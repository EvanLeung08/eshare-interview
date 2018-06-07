package com.eshare.interview.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Json操作测试
 *
 * @author liangyh
 * @date 2018/6/6
 */
public class JsonTest2 {



    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("what is the product name? ");
            String productName = in.next();
            if (!Product.findByName(productName)) {
                System.out.println("Sorry，that product was not found in our inventory. Do you want to add one ?(Y/N)");
                if ("Y".equalsIgnoreCase(in.next())) {
                    System.out.println("Please input the product price:");
                    Double price = Double.parseDouble(in.next());
                    System.out.println("Please input the product quantity:");
                    Integer quantity = Integer.parseInt(in.next());
                    Product product = new Product(productName, price, quantity);
                    Product.add(product);
                    System.out.println("The produce has been saved sucessfully");
                    //休眠1秒
                    Thread.sleep(1000);
                }else {
                    break;
                }
            } else {
                break;
            }
        }
        //休眠2秒再关闭
        Thread.sleep(2000);
        in.close();
    }



    /**
     * 文件工具类
     */
    static class FileUtil {
        /**
         * 读取文件
         *
         * @param path
         * @return
         */
        public static String readFile(String path) {

            FileInputStream inputStream = null;
            Scanner sc = null;
            StringBuilder sb = new StringBuilder();
            try {
                inputStream = new FileInputStream(path);
                sc = new Scanner(inputStream, "UTF-8");
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (sc != null) {
                    sc.close();
                }
            }
            return sb.toString();
        }

        /**
         * 写入文件
         *
         * @param data
         * @param filePath
         * @return
         */
        public static boolean write2File(String data, String filePath) {
            FileOutputStream fos = null;
            boolean status = true;
            try {
                fos = new FileOutputStream(filePath);
                fos.write(data.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        status = false;
                    }
                }
            }
            return status;
        }
    }


    /**
     * 产品实体类
     */
    static class Product {
        /**
         * 名称
         */
        private String name;
        /**
         * 价格
         */
        private Double price;
        /**
         * 数量
         */
        private Integer quantity;
        /**
         * 产品数据
         */
        private static String data;

        private static List<Product> products;
        /**
         * 存储文件路径
         */
        private static String filePath;

        public Product() {

        }

        public Product(String name, Double price, Integer quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        static {
            init();
        }

        /**
         * 初始化测试数据
         */
        private static void init() {
            filePath = JsonTest2.class.getClassLoader().getResource("data.json").getPath();
            data = FileUtil.readFile(filePath);
            JSONObject productData = JSONObject.parseObject(data);
            JSONArray productsJson = productData.getJSONArray("products");
            products = productsJson.toJavaList(Product.class);
        }

        /**
         * 通过产品名称查找对应产品
         *
         * @param productName
         * @return
         */
        public static boolean findByName(String productName) {
            for (Product product : products) {
                if (productName.equals(product.getName())) {
                    //输出结果到控制台
                    System.out.println(product.toString());
                    return true;
                }
            }
            return false;
        }

        /**
         * 添加产品
         *
         * @param product
         * @return
         */
        public static boolean add(Product product) {
            products.add(product);
            return save2File(products);
        }

        public static boolean save2File(List<Product> products) {
            String jsonStr = "{\"products\":"+JSON.toJSONString(products)+"}";
            return FileUtil.write2File(jsonStr, Product.filePath);
        }

        @Override
        public String toString() {
            return
                    "name='" + name + '\'' +
                            ", Price:$" + price +
                            ", Quantity On hand:" + quantity;
        }
    }

}
