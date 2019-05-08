# netty-studying
### 学习使用netty

> lesson01 使用传统io
>
> lesson02 使用netty 
> 
> lesson03 使用netty，方法简单封装，可以进行会话演示
>
> index.html 演示界面
```
使用不能浏览器开两个会话窗口
// 先连接
{"messageType":"CONNECT","fromId":1,"receiverId":2,"message":"我是1"}
{"messageType":"CONNECT","fromId":2,"receiverId":3,"message":"我是2"}

//进行会话
{"messageType":"CHAT","fromId":1,"receiverId":2,"message":"我是1"}
{"messageType":"CHAT","fromId":2,"receiverId":3,"message":"我是2"}
```