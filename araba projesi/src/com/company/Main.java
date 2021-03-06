package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Vehicle implements Comparable<Vehicle>{
    String plaka;
    String model;
    String marka;

    //constructor
    public Vehicle(String plaka, String model, String marka) {
        this.plaka = plaka;
        this.model = model;
        this.marka = marka;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plaka='" + plaka + '\'' +
                ", model='" + model + '\'' +
                ", marka='" + marka + '\'' +
                '}';
    }

    public int compareTo(Vehicle otherVehicle) {
        if (plaka.equals(otherVehicle.plaka)){
            return 0;
        }
        else if (plaka.compareTo(otherVehicle.plaka)<0){
            return -1;
        }
        else {
            return 1;
        }

    }
    public static Vehicle[] addingVehicle(Vehicle[] vehicles,Vehicle eklenen){
        Vehicle[] yeni = new Vehicle[vehicles.length + 1];
        System.arraycopy(vehicles, 0, yeni, 0, vehicles.length);
        yeni[yeni.length-1]=eklenen;

        return yeni;
    }


}
class Car extends Vehicle{
    int kapisayisi;
    //constructor
    public Car(String plaka,String model,String marka,int kapisayisi){
        super(plaka,model,marka);
        this.kapisayisi=kapisayisi;
    }
    public int getKapisayisi() {
        return kapisayisi;
    }
    public void setKapisayisi(int kapisayisi) {
        this.kapisayisi = kapisayisi;
    }

    @Override
    public String toString() {
        if(getKapisayisi() == 4){
            return  "ARABA"+
                    "  Tipi:"+" Standart Araba"+
                    "  Plakas??:"+getPlaka() +
                    "  Modeli:" +getModel() +
                    "  Markas??:" +getMarka() +
                    "  Kap?? Say??s??:" + getKapisayisi() ;

        }else if(getKapisayisi() == 2){
            return  "ARABA" +
                    "  Tipi:"+" Spor Araba"+
                    "  Plakas??:"+getPlaka() +
                    "  Modeli:" +getModel() +
                    "  Markas??:" +getMarka() +
                    "  Kap?? Say??s??:" + getKapisayisi() ;

        }else if(getKapisayisi() == 5){
            return  "ARABA" +
                    "  Tipi:"+" SWagon Araba"+
                    "  Plakas??:"+getPlaka() +
                    "  Modeli:" +getModel() +
                    "  Markas??:" +getMarka() +
                    "  Kap?? Say??s??:" + getKapisayisi() ;

        }else{
            return  "ARABA" +
                    "  Tipi:"+" Verilmemi??"+
                    "  Plakas??:"+getPlaka() +
                    "  Modeli:" +getModel() +
                    "  Markas??:" +getMarka() +
                    "  Kap?? Say??s??:" + getKapisayisi() ;
        }
    }
}

class Truck extends Vehicle{
    int yukkapasitesi;
    public Truck(String plaka,String model,String marka,int yukkapasitesi){
        super(plaka,model,marka);
        this.yukkapasitesi=yukkapasitesi;
    }
    public int getYukkapasitesi() {
        return yukkapasitesi;
    }
    public void setYukkapasitesi(int yukkapasitesi) {
        this.yukkapasitesi = yukkapasitesi;
    }

    @Override
    public String toString() {
        return  "KAMYON" +
                "  Plakas??:"+getPlaka()+
                "  Modeli:"+getModel()+
                "  Markas??:"+getMarka()+
                "  Y??k Kapasitesi:"+getYukkapasitesi();
    }
}
class IkiY??nl??Liste{
    public ListNode head;
    public ListNode tail;

    public class ListNode{

        public Vehicle data;
        public ListNode previous;
        public ListNode next;
        public ListNode(Vehicle data){
            this.data=data;
        }
        public void yazd??rma(){
            System.out.println(data);
        }


    }
    public IkiY??nl??Liste(){
        this.head=null;
        this.tail=null;
    }

    public void add(Vehicle item){
        ListNode eklenen=new ListNode(item);
        ListNode olan= null;
        if(head == null){
            head = tail = eklenen;
            head.previous = null;
            tail.next = null;

        }
        else if(1 == head.data.compareTo(item)){
            eklenen.next = head;
            eklenen.next.previous = eklenen;
            head = eklenen;
        }
        else {
            olan=head;
            while (olan.next!=null && olan.next.data.compareTo(item) == -1){
                olan=olan.next;
            }
            eklenen.next=olan.next;
            if(olan.next!=null){//eklenen veri sonda de??ilse ??nceki veriyi sonrakine ba??l??yor
                eklenen.next.previous=eklenen;
            }
            olan.next = eklenen;
            eklenen.previous = olan;

        }

    }

    public ListNode delete(String plaka){
        ListNode value=head;
        while (value.data.getPlaka()==null ? plaka!=null: !value.data.getPlaka().equals(plaka)){
            value=value.next;
            if (value==null){
                return null;
            }
        }
        if (value==head){
            head=value.next;
        }
        else if (value==tail){
            tail=value.previous;
            tail.next=null;
        }
        else {
            value.previous.next=value.next;
            value.next.previous=value.previous;
        }
        return value;
    }
    public void listecikti(){
        ListNode bas=head;
        while(bas != null){
            bas.yazd??rma();
            bas=bas.next;
        }
        System.out.println("");
    }

    public void terslistecikti(){
        System.out.println("----TERS L??STE----");
        ListNode son=head;
        ListNode bas=tail;
        while(bas.next != null){
            bas=bas.next;
        }
        while(bas != son){
            bas.yazd??rma();
            bas=bas.previous;
        }
        son.yazd??rma();
        }
    }

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner alinanveri=new Scanner(new File("veriler.txt"));
        Vehicle[] tasitlar=new Vehicle[0];
        IkiY??nl??Liste liste1=new IkiY??nl??Liste();
        while (alinanveri.hasNext()){  //verilerin okunma ad??m??
            String tur=alinanveri.next();
            if (tur.equals("car")){ //ilk veri car ise burdan devam ediyor
                String plaka=alinanveri.next();
                String model=alinanveri.next();
                String marka=alinanveri.next();
                int kapisayisi=alinanveri.nextInt();
                Car araba=new Car(plaka,model,marka,kapisayisi);
                tasitlar=Vehicle.addingVehicle(tasitlar,araba); //bu listede t??m tipleri toplad??k
                liste1.add(araba);
            }
            else {
                String plaka=alinanveri.next();
                String model=alinanveri.next();
                String marka=alinanveri.next();
                int yukkapasitesi=alinanveri.nextInt();
                Truck kamyon=new Truck(plaka,model,marka,yukkapasitesi);
                tasitlar=Vehicle.addingVehicle(tasitlar,kamyon);
                liste1.add(kamyon);
            }
        }
        System.out.println("----????LEMLERDEN ??NCEK?? SIRALANMI?? L??STE-----");
        liste1.listecikti();
        IkiY??nl??Liste liste2=new IkiY??nl??Liste();
        for(Vehicle a:tasitlar){ //t??m tiplerin oldu??u listeyi ba??ka bir listeye aktard??m
            liste2.add(a);
        }
        Scanner islemveri = new Scanner(new File("islemler.txt"));
        //alinanveri.useDelimiter("\\s+|\n");

        String[] i = new String[100];
        while(islemveri.hasNext()){
            i = islemveri.nextLine().split("\\s+");
            for(String a : i){                //ge??ici de??erler
                if(a.equals("ekle")){        //i??lem
                    if(i[1].equals("car")){
                        Car araba = new Car(i[2],i[3],i[4],Integer.parseInt(i[5]));   //de??erleri indexe g??re ald??k
                        liste2.add(araba);
                    }
                    else{
                        Truck kamyon = new Truck(i[2],i[3],i[4],Integer.parseInt(i[5])); //de??erleri indexe g??re ald??k
                        liste2.add(kamyon);
                        }
                    }
                else {
                    liste2.delete(i[1]);
                }
                }
            }
        System.out.println("----????LEMLERDEN SONRAK?? SIRALANMI?? L??STE----");
        liste2.listecikti();
        liste2.terslistecikti();
        }

}