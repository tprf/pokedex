package com.example.pokedex.modele;

import java.util.List;

public class Pokemon {
    private int Id;

    private String Num;

    private String Name;

    private String Img;

    private List<String> type;

    private String Height;

    private String Weight;

    private String Candy;

    private int CandyCount;

    private String Egg;

    private double SpawnChance;

    private double AvgSpawns;

    private String SpawnTime;
    private List<Double>  Multipliers;

    private List<String> Weaknesses;

    private List<NextEvolution> next_evolution;

    private List<PreEvolution> prev_evolution;

    public Pokemon()
    {
    }

    public Pokemon(int id, String num, String name, String img, List<String> type, String height, String weight, String candy, int candyCount, String egg, double spawnChance, double avgSpawns, String spawnTime, List<Double> multipliers, List<String> weaknesses, List<NextEvolution> next_evolution, List<PreEvolution> prev_evolution) {
        Id = id;
        Num = num;
        Name = name;
        Img = img;
        this.type = type;
        Height = height;
        Weight = weight;
        Candy = candy;
        CandyCount = candyCount;
        Egg = egg;
        SpawnChance = spawnChance;
        AvgSpawns = avgSpawns;
        SpawnTime = spawnTime;
        Multipliers = multipliers;
        Weaknesses = weaknesses;
        this.next_evolution = next_evolution;
        this.prev_evolution = prev_evolution;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getCandy() {
        return Candy;
    }

    public void setCandy(String candy) {
        Candy = candy;
    }

    public int getCandyCount() {
        return CandyCount;
    }

    public void setCandyCount(int candyCount) {
        CandyCount = candyCount;
    }

    public String getEgg() {
        return Egg;
    }

    public void setEgg(String egg) {
        Egg = egg;
    }

    public double getSpawnChance() {
        return SpawnChance;
    }

    public void setSpawnChance(double spawnChance) {
        SpawnChance = spawnChance;
    }

    public double getAvgSpawns() {
        return AvgSpawns;
    }

    public void setAvgSpawns(double avgSpawns) {
        AvgSpawns = avgSpawns;
    }

    public String getSpawnTime() {
        return SpawnTime;
    }

    public void setSpawnTime(String spawnTime) {
        SpawnTime = spawnTime;
    }

    public List<Double> getMultipliers() {
        return Multipliers;
    }

    public void setMultipliers(List<Double> multipliers) {
        Multipliers = multipliers;
    }

    public List<String> getWeaknesses() {
        return Weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        Weaknesses = weaknesses;
    }

    public List<NextEvolution> getNext_evolution() {
        return next_evolution;
    }

    public void setNext_evolution(List<NextEvolution> next_evolution) {
        this.next_evolution = next_evolution;
    }

    public List<PreEvolution> getPrev_evolution() {
        return prev_evolution;
    }

    public void setPrev_evolution(List<PreEvolution> prev_evolution) {
        this.prev_evolution = prev_evolution;
    }
}
