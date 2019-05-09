package com.bfchengnuo.uselibraries.apache.commons;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Created by 冰封承諾Andy on 2019/5/8.
 *
 * 提供的 Builder 工具类用来方便的重写 toString、hashCode、equals、compareTo
 */
public class BuilderTest implements Comparable<BuilderTest> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        BuilderTest builderTest = new BuilderTest();
        builderTest.setName("Loli");
        System.out.println(builderTest);
    }

    /**
     * ToStringBuilder 使用
     */
    @Override
    public String toString() {
        // return new ToStringBuilder(this).append(this.getName()).toString();
        // 输出格式为  BuilderTest@f6f4d33[name=Loli]
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * HashCodeBuilder 使用;
     * PS： hashCode 函数要设计的尽量简单，避免重运算，要不然会导致拖慢整体的效率
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
        // return new HashCodeBuilder().append(this.getName()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int compareTo(BuilderTest o) {
        return CompareToBuilder.reflectionCompare(this, o);
    }
}
