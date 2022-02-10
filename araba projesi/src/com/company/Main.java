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
                    "  Plakası:"+getPlaka() +
                    "  Modeli:" +getModel() +
                    "  Markası:" +getMarka() +
                    "  Kapı Sayısı:" + getKapisayisi() ;

        }else if(getKapisayisi() == 2){
            return  "ARABA" +
                    "  Tipi:"+" Spor Araba"+
                    "  Plakası:"+getPlaka() +
                    "  Modeli:" +getModel() +
                    "  Markası:" +getMarka() +
                    "  Kapı Sayısı:" + getKapisayisi() ;

        }else if(getKapisayisi() == 5){
            return  "ARABA" +
                    "  Tipi:"+" SWagon Araba"+
                    "  Plakası:"+getPlaka() +
                    "  Modeli:" +getModel() +
                    "  Markası:" +getMarka() +
                    "  Kapı Sayısı:" + getKapisayisi() ;

        }else{
            return  "ARABA" +
                    "  Tipi:"+" Verilmemiş"+
                    "  Plakası:"+getPlaka() +
                    "  Modeli:" +getModel() +
                    "  Markası:" +getMarka() +
                    "  Kapı Sayısı:" + getKapisayisi() ;
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
                "  Plakası:"+getPlaka()+
                "  Modeli:"+getModel()+
                "  Markası:"+getMarka()+
                "  Yük Kapasitesi:"+getYukkapasitesi();
    }
}
class IkiYönlüListe{
    public ListNode head;
    public ListNode tail;

    public class ListNode{

        public Vehicle data;
        public ListNode previous;
        public ListNode next;
        public ListNode(Vehicle data){
            this.data=data;
        }
        public void yazdırma(){
            System.out.println(data);
        }


    }
    public IkiYönlüListe(){
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
            if(olan.next!=null){//eklenen veri sonda değilse önceki veriyi sonrakine bağlıyor
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
            bas.yazdırma();
            bas=bas.next;
        }
        System.out.println("");
    }

    public void terslistecikti(){
        System.out.println("----TERS LİSTE----");
        ListNode son=head;
        ListNode bas=tail;
        while(bas.next != null){
            bas=bas.next;
        }
        while(bas != son){
            bas.yazdırma();
            bas=bas.previous;
        }
        son.yazdırma();
        }
    }

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner alinanveri=new Scanner(new File("veriler.txt"));
        Vehicle[] tasitlar=new Vehicle[0];
        IkiYönlüListe liste1=new IkiYönlüListe();
        while (alinanveri.hasNext()){  //verilerin okunma adımı
            String tur=alinanveri.next();
            if (tur.equals("car")){ //ilk veri car ise burdan devam ediyor
                String plaka=alinanveri.next();
                String model=alinanveri.next();
                String marka=alinanveri.next();
                int kapisayisi=alinanveri.nextInt();
                Car araba=new Car(plaka,model,marka,kapisayisi);
                tasitlar=Vehicle.addingVehicle(tasitlar,araba); //bu listede tüm tipleri topladık
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
        System.out.println("----İŞLEMLERDEN ÖNCEKİ SIRALANMIŞ LİSTE-----");
        liste1.listecikti();
        IkiYönlüListe liste2=new IkiYönlüListe();
        for(Vehicle a:tasitlar){ //tüm tiplerin olduğu listeyi başka bir listeye aktardım
            liste2.add(a);
        }
        Scanner islemveri = new Scanner(new File("islemler.txt"));
        //alinanveri.useDelimiter("\\s+|\n");

        String[] i = new String[100];
        while(islemveri.hasNext()){
            i = islemveri.nextLine().split("\\s+");
            for(String a : i){                //geçici değerler
                if(a.equals("ekle")){        //işlem
                    if(i[1].equals("car")){
                        Car araba = new Car(i[2],i[3],i[4],Integer.parseInt(i[5]));   //değerleri indexe göre aldık
                        liste2.add(araba);
                    }
                    else{
                        Truck kamyon = new Truck(i[2],i[3],i[4],Integer.parseInt(i[5])); //değerleri indexe göre aldık
                        liste2.add(kamyon);
                        }
                    }
                else {
                    liste2.delete(i[1]);
                }
                }
            }
        System.out.println("----İŞLEMLERDEN SONRAKİ SIRALANMIŞ LİSTE----");
        liste2.listecikti();
        liste2.terslistecikti();
        }

}