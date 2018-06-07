编写一个程序，以产品名(name)为输入，查找该产品当前的价格（price）和数童〔quantity)。产品数据保存在JSON格式的数据文件中，看上去是这样的:
```json
{
    "products": [
        {
            "name": "Widget",
            "price": 25.00,
            "quantity": 5
        },
        {
            "name": "Thing",
            "price": 15.00,
            "quantity": 5
        },
        {
            "name": "DOOdad",
            "price": 5.00,
            "quantity": 10
        }
    ]
}
```
如果找到该产品，打印产品名，以及对应的价格和数量。如果没找到，
则提示没有该产品，井重新开始。


示例输出
```text
what is the product name? iPad
Sorry，that product was not found in our inventory，
What is the product name ? Widget
Name:Widget
Price:$25.00
Quantity On hand:5
```


约束
文件是JSON格式的。使用某种JSON解析器，拉取出文件中的值.
如果没发现记录，再次提示。

挑战
确保产品搜索区分大小写。
如果某个产品没有找到，询问用户是否应该添加该产品。如果是，咨询价格和数量，并将其保存到JS0N文件中。确保新添加的产品可以立即搜索到，而不用重启程序。