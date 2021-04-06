1、EventBus 简介
EventBus是一种用于Android的事件发布-订阅总线，由GreenRobot开发，Gihub地址是：EventBus。它简化了应用程序内各个组件之间进行通信的复杂度，尤其是碎片之间进行通信的问题，可以避免由于使用广播通信而带来的诸多不便。

1.1 三个角色
Event：事件，它可以是任意类型，EventBus会根据事件类型进行全局的通知。
Subscriber：事件订阅者，在EventBus 3.0之前我们必须定义以onEvent开头的那几个方法，分别是onEvent、onEventMainThread、onEventBackgroundThread和onEventAsync，而在3.0之后事件处理的方法名可以随意取，不过需要加上注解@subscribe，并且指定线程模型，默认是POSTING。
Publisher：事件的发布者，可以在任意线程里发布事件。一般情况下，使用EventBus.getDefault()就可以得到一个EventBus对象，然后再调用post(Object)方法即可。
1.2 四种线程模型
EventBus3.0有四种线程模型，分别是：

POSTING：默认，表示事件处理函数的线程跟发布事件的线程在同一个线程。
MAIN：表示事件处理函数的线程在主线程(UI)线程，因此在这里不能进行耗时操作。
BACKGROUND：表示事件处理函数的线程在后台线程，因此不能进行UI操作。如果发布事件的线程是主线程(UI线程)，那么事件处理函数将会开启一个后台线程，如果果发布事件的线程是在后台线程，那么事件处理函数就使用该线程。
ASYNC：表示无论事件发布的线程是哪一个，事件处理函数始终会新建一个子线程运行，同样不能进行UI操作。
