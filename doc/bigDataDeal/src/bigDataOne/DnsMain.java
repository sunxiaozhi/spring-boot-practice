package bigDataOne;

import bigDataOne.entity.DnsLog;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 海量数据处理
 *
 * @author sunhuanzhi
 * @date 2022/4/17 10:26
 */
public class DnsMain {
    static String fileLoc = "E:\\workspace\\spring-boot-practice\\doc\\bigDataDeal\\src\\bigDataOne\\source\\dnsData\\dnsData.txt";
    //static String fileLoc = "\\..\\source\\dnsData\\dnsData.txt";

    public static void findDnsLog() throws IOException, ClassNotFoundException {
        long start = System.currentTimeMillis();
        hashToSmallFiles();
        long end1 = System.currentTimeMillis();
        System.out.println("将大文件映射成小文件，用时：" + (end1 - start) + "毫秒");

        System.out.println("映射到小文件完成，开始统计每个小文件中出现频率最高的DnsLog");
        long start1 = System.currentTimeMillis();
        List<DnsLog> list = countEverySmallFile();
        long end2 = System.currentTimeMillis();
        System.out.println("统计所有文件共用时：" + (end2 - start1) + " 毫秒");

        System.out.println("统计完成，开始计算所有url中出现频率最高的url");
        DnsLog dnsLog = calculateResult(list);
        System.out.println("访问次数最多的url是：" + dnsLog.getUrl() + " 访问次数是：" + dnsLog.getCount());
        long end = System.currentTimeMillis();
        System.out.println("共用时：" + (end - start) + "毫秒");
    }

    /**
     * 从每个文件出现频率最高url中，计算出所有文件中出现频率最高url。
     */
    private static DnsLog calculateResult(List<DnsLog> list) {
        DnsLog[] dnsLogs = new DnsLog[list.size()];
        dnsLogs = list.toArray(dnsLogs);
        int max = 0;
        for (int j = 1; j < dnsLogs.length; j++) {
            if (dnsLogs[j].getCount() > dnsLogs[max].getCount()) {
                max = j;
            }
        }
        return dnsLogs[max];
    }

    /**
     * 统计生成的每一个小文件，返回一个List,这个List的每一项就是每个小文件的统计结果，即每个小文件中出现频率最高的url和出现次数
     */
    private static List<DnsLog> countEverySmallFile() throws FileNotFoundException, IOException {
        //String pattern = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";
        //Pattern p = Pattern.compile(pattern);

        List<DnsLog> list = new ArrayList<DnsLog>();
        for (int i = 0; i < 1024; i++) {
            File file = new File(fileLoc + i + ".txt");
            if (file.exists()) {
                long startTime = System.currentTimeMillis();
                BufferedReader br1 = new BufferedReader(new FileReader(file));
                String dnsLog1 = "";
                HashMap<String, Integer> hm = new HashMap<String, Integer>();
                while ((dnsLog1 = br1.readLine()) != null) {
                    //Matcher m = p.matcher(dnsLog1);
                    //boolean mFind = m.find();
                    String[] dnsLogArray = dnsLog1.split("\t");
                    if (!hm.containsKey(dnsLogArray[2])) {
                        //  hm.put(dnsLog1, 1);
                        hm.put(dnsLogArray[2], 1);
                    } else {
                        //hm.put(dnsLog1, hm.get(dnsLog1) + 1);
                        hm.put(dnsLogArray[2], hm.get(dnsLogArray[2]) + 1);
                    }
                }

                DnsLog[] dnsLogs = new DnsLog[hm.size()];
                int index = 0;
                for (String temp : hm.keySet()) {
                    dnsLogs[index] = new DnsLog(temp, hm.get(temp));
                    index++;
                }
                int max = 0;
                for (int j = 1; j < dnsLogs.length; j++) {
                    if (dnsLogs[j].getCount() > dnsLogs[max].getCount()) {
                        max = j;
                    }
                }
                list.add(dnsLogs[max]);
                long endTime = System.currentTimeMillis();
                System.out.println("已经统计文件：" + fileLoc + i + ".txt，用时：" + (endTime - startTime) + " 毫秒");
            }
        }
        return list;
    }

    /**
     * 将打文件hash成1024个小文件
     */
    private static void hashToSmallFiles() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileLoc));
        String dnsLog = "";
        HashMap<String, FileWriter> fileWriters = new HashMap<String, FileWriter>();
        while ((dnsLog = br.readLine()) != null) {
            int tmp = Math.abs(dnsLog.hashCode() % 1024);
            String fileName = fileLoc + tmp + ".txt";
            FileWriter fw = null;
            if (fileWriters.containsKey(fileName)) {
                fw = fileWriters.get(fileName);
            } else {
                fw = new FileWriter(fileName, true);
                fileWriters.put(fileName, fw);
            }
            fw.write(dnsLog + "\n");
        }
        br.close();
        for (FileWriter ff : fileWriters.values()) {
            ff.close();
        }
    }

    /**
     * 随机生成DnsLog地址，生成大文本文件
     */
    private static void generateFile() throws IOException {
        FileWriter fw = new FileWriter(fileLoc, true);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                fw.write(generateDnsLog() + "\n");
            }
        }
        fw.close();
        System.out.println("done");
    }

    /**
     * 随机生成DnsLog地址
     */
    private static String generateDnsLog() {
        StringBuilder dnsLog = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int temp = (int) (Math.random() * 255);
            dnsLog.append(temp).append(".");
        }
        return dnsLog.substring(0, dnsLog.length() - 1);
    }

    public static void main(String[] args) {
        try {
            findDnsLog();
            //generateFile();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
