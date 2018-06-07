package com.eshare.interview.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.List;
import java.util.Scanner;

/**
 * Json操作测试
 *
 * @author liangyh
 * @date 2018/6/6
 */
public class JsonTest {

    private static String DATA;

    static {
        //初始化测试数据
        DATA = "{\n" +
                "    \"products\": [\n" +
                "        {\n" +
                "            \"name\": \"Widget\",\n" +
                "            \"price\": 25.00,\n" +
                "            \"quantity\": 5\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Thing\",\n" +
                "            \"price\": 15.00,\n" +
                "            \"quantity\": 5\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"DOOdad\",\n" +
                "            \"price\": 5.00,\n" +
                "            \"quantity\": 10\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("what is the product name? ");
            if (!Product.findByName(in.next())) {
                System.out.println("Sorry，that product was not found in our inventory.");
            } else {
                break;
            }
        }
        in.close();
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

        /**
         * 通过产品名称查找对应产品
         * @param productName
         * @return
         */
        public static boolean findByName(String productName) {
            JSONObject productData = JSON.parseObject(DATA);
            JSONArray productsJson = productData.getJSONArray("products");
            List<Product> products = productsJson.toJavaList(Product.class);
            for (Product product : products) {
                if (productName.equals(product.getName())) {
                    //输出结果到控制台
                    System.out.println(product.toString());
                    return true;
                }
            }
            return false;
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
