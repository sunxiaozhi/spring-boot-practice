package bigDataOne;

import bigDataOne.entity.Ip;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 海量数据处理
 *
 * @author sunhuanzhi
 * @date 2022/4/17 10:26
 */
public class IpMain {
    //static String fileLoc = "E:\\bigData\\dns\\dns.txt";
    static String fileLoc = "E:\\workspace\\spring-boot-practice\\doc\\bigDataDeal\\src\\bigDataOne\\source\\ipData\\ipData.txt";

    public static void findIp() throws IOException, ClassNotFoundException {
        long start = System.currentTimeMillis();
        hashToSmallFiles();
        long end1 = System.currentTimeMillis();
        System.out.println("将大文件映射成小文件，用时：" + (end1 - start) + "毫秒");

        System.out.println("映射到小文件完成，开始统计每个小文件中出现频率最高的ip");
        long start1 = System.currentTimeMillis();
        List<Ip> list = countEverySmallFile();
        long end2 = System.currentTimeMillis();
        System.out.println("统计所有文件共用时：" + (end2 - start1) + " 毫秒");

        System.out.println("统计完成，开始计算所有ip中出现频率最高的ip");
        Ip ip = calculateResult(list);
        System.out.println("访问次数最多的ip是：" + ip.getIp() + " 访问次数是：" + ip.getCount());
        long end = System.currentTimeMillis();
        System.out.println("共用时：" + (end - start) + "毫秒");
    }

    /**
     * 从每个文件出现频率最高ip中，计算出所有文件中出现频率最高ip。
     */
    private static Ip calculateResult(List<Ip> list) {
        Ip[] ips = new Ip[list.size()];
        ips = list.toArray(ips);
        int max = 0;
        for (int j = 1; j < ips.length; j++) {
            if (ips[j].getCount() > ips[max].getCount()) {
                max = j;
            }
        }
        return ips[max];
    }

    /**
     * 统计生成的每一个小文件，返回一个List,这个List的每一项就是每个小文件的统计结果，即每个小文件中出现频率最高的ip和出现次数
     */
    private static List<Ip> countEverySmallFile() throws FileNotFoundException, IOException {
        List<Ip> list = new ArrayList<Ip>();
        for (int i = 0; i < 1024; i++) {
            File file = new File(fileLoc + i + ".txt");
            if (file.exists()) {
                long startTime = System.currentTimeMillis();
                BufferedReader br1 = new BufferedReader(new FileReader(file));
                String ip1 = "";
                HashMap<String, Integer> hm = new HashMap<String, Integer>();
                while ((ip1 = br1.readLine()) != null) {
                    if (!hm.containsKey(ip1)) {
                        hm.put(ip1, 1);
                    } else {
                        hm.put(ip1, hm.get(ip1) + 1);
                    }
                }

                Ip[] ips = new Ip[hm.size()];
                int index = 0;
                for (String temp : hm.keySet()) {
                    ips[index] = new Ip(temp, hm.get(temp));
                    index++;
                }
                int max = 0;
                for (int j = 1; j < ips.length; j++) {
                    if (ips[j].getCount() > ips[max].getCount()) {
                        max = j;
                    }
                }
                list.add(ips[max]);
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
        String ip = "";
        HashMap<String, FileWriter> fileWriters = new HashMap<String, FileWriter>();
        while ((ip = br.readLine()) != null) {
            int tmp = Math.abs(ip.hashCode() % 1024);
            String fileName = fileLoc + tmp + ".txt";
            FileWriter fw = null;
            if (fileWriters.containsKey(fileName)) {
                fw = fileWriters.get(fileName);
            } else {
                fw = new FileWriter(fileName, false);
                fileWriters.put(fileName, fw);
            }
            fw.write(ip + "\n");
        }
        br.close();
        for (FileWriter ff : fileWriters.values()) {
            ff.close();
        }
    }

    /**
     * 随机生成ip地址，生成大文本文件
     */
    private static void generateFile() throws IOException {
        FileWriter fw = new FileWriter(fileLoc, true);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                fw.write(generateIp() + "\n");
            }
        }
        fw.close();
        System.out.println("done");
    }

    /**
     * 随机生成ip地址
     */
    private static String generateIp() {
        StringBuilder ip = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int temp = (int) (Math.random() * 255);
            ip.append(temp).append(".");
        }
        return ip.substring(0, ip.length() - 1);
    }

    public static void main(String[] args) {
        try {
            findIp();
            //generateFile();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
