package com.bfchengnuo.basic

/**
 * @author 冰封承諾Andy
 * @date 2020/6/16
 */

fun main() {
    // 类似于 (name: String -> User)
    val userFun = ::User
    println(userFun("skye").print())
    val names = listOf<User>(
            User("mps"),
            User("amy"),
            User("emmelie")
    ).map(User::name)
    println(names)

    val basic = BasicDemo()
    // 高阶函数
    basic.func(2)
    println(basic.fun1(22))
    println(basic.add(2))
    // 单个参数隐式名称
    // = item -> basic.echp(item)
    listOf<Int>(1, 2, 3, 4).forEach { basic.echo(it) }
}

class User(var name: String) {
    fun print() = this.name
}