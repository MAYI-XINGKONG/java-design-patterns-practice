package com.niudada;


public class Main {
    public static void main(String[] args) {
        Immortal immortal = new BasicImmortal();
        System.out.println("普通修仙者能力展示：");
        System.out.println("灵力修为：" + immortal.getPower());
        immortal.attack();

        // 装备青龙宝剑
        Immortal swordImmortal = new SwordDecorator(immortal);
        System.out.println("\n装备青龙宝剑之后的修仙者：");
        System.out.println("灵力修为：" + swordImmortal.getPower());
        swordImmortal.attack();

        // 同时装备青龙宝剑和玄武盾牌
        Immortal shieldImmortal = new ShieldDecorator(new SwordDecorator(immortal));
        System.out.println("\n同时装备青龙宝剑和玄武盾牌之后的修仙者：");
        System.out.println("灵力修为：" + shieldImmortal.getPower());
        shieldImmortal.attack();
        shieldImmortal.retreat();

    }
}