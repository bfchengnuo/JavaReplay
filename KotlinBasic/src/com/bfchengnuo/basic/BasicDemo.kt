package com.bfchengnuo.basic

/**
 * 基础语法
 * @author 冰封承諾Andy
 * @date 2020/6/16
 */
class BasicDemo {
    var str: String = "Mps"

    // final 类型
    val arr = intArrayOf(1, 2, 3, 4)

    // 函数定义，省略返回值（自动推断）
    fun sum(x: Int, y: Int) = x + y

    // if 是一个表达式
    fun foo(x: Int): Int = if (x == 0) 1 else x * foo(x - 1)

    // 高阶函数
    fun func(x: Int) {
        fun double(y: Int): Int {
            return y * 2
        }

        println(double(x))
    }

    // 函数签名定义，作为参数
    // ？表示可以为空，并且可以返回另一个函数
    fun execFun(exec: (msg: String?) -> Unit,
                pop: (() -> Unit)?,
                rtn: ((Int) -> ((Int) -> Unit))?,
                rtn2: (((Int) -> Int) -> Unit)?) {
        exec("skye")
    }

    // 匿名函数
    val fun1 = fun(x: Int): String { return "to str: $x" }

    // lambda 表达式，必须使用 {} 包裹，适合的情况下可以进行定义省略
    val sum1: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    val sum2 = { x: Int, y: Int -> x + y }
    val sum3: (Int, Int) -> Int = { x, y -> x + y }
    // fun 方式定义，类似闭包；sum4(1)(2), 柯里化语法
    // kotlin 的闭包可以修改外部变量
    fun sum4(a: Int) = { b:Int -> a + b }
    val add = { x: Int ->
        val y = x + 1
        // 最后一行表达式类型就是返回类型
        y
    }

    fun echo(x: Int) {
        println(x)
    }
}