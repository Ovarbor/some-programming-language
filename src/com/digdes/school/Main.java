package com.digdes.school;

public class Main {
    public static void main(String[] args) {

        JavaSchoolStarter javaSchoolStarter = new JavaSchoolStarter();

        try {
            javaSchoolStarter.insert("insert values 'lastName' = 'Чушкин' , 'id'=4, 'coast'=10.1, 'active'=true");
            System.out.println("INSERT OK");
            javaSchoolStarter.insert("INSERT VALUES 'lastName' = 'Иванов' , 'id'=18, 'age'=43, 'active'=false");
            System.out.println("INSERT OK");
            javaSchoolStarter.insert("INsERT VALUeS 'lastName' = 'Петров' , 'active'= true");
            System.out.println("INSERT OK");
            javaSchoolStarter.insert("INSERT VALUES 'lastName' = 'Барашкин' , 'id'=16, 'age'=55, 'coast'=45.8, 'active'=false");
            System.out.println("INSERT OK");
            javaSchoolStarter.insert("INSERT VALUES 'lastName' = 'Хрюшкин' , 'id'=1, 'age'=55, 'coast'=20.4, 'active'=true");
            System.out.println("INSERT OK");
            javaSchoolStarter.insert("INSERT VALUES 'lastName' = 'Плюшкин' , 'id'=2, 'age'=24, 'coast'=15.8, 'active'=false");
            System.out.println("INSERT OK");
            javaSchoolStarter.insert("INSERT VALUES 'lastName' = 'Свинюшкин' , 'id'=3, 'age'=31, 'coast'=35.7, 'active'=true");
            System.out.println("INSERT OK");

            System.out.println(javaSchoolStarter.getResults("   SeLeCT where 'coast' >= 4 aNd 'id' <= 17"));

            javaSchoolStarter.update("uPdatE vaLues 'lastname' = 'Макаров', 'id' = 5, 'age'= 28 where 'lastName' = 'Петров'");
            System.out.println("UPDATE OK");
            javaSchoolStarter.update("uPdatE vaLues 'coast' = '17.73' where 'lastname' = Макаров");
            System.out.println("UPDATE OK");
            javaSchoolStarter.update("uPdatE vaLues 'coast' = '9.73' where 'id' = 18");
            System.out.println("UPDATE OK");
            javaSchoolStarter.update("uPdatE vaLues 'age' = '34', 'active' = false where 'coast' = 10.1");
            System.out.println("UPDATE OK");
            javaSchoolStarter.update("uPdatE vaLues 'age' = '36' where 'age' = 34");
            System.out.println("UPDATE OK");

            System.out.println(javaSchoolStarter.getResults("   SeLeCT where 'lastName' ilike 'рОв'  "));

            System.out.println(javaSchoolStarter.getResults("   SeLeCT where 'id' !=3  or 'coast' <= 35"));


            javaSchoolStarter.delete("   DELETE   ");

            System.out.println(javaSchoolStarter.getResults("   SeLeCT    "));

            javaSchoolStarter.insert("INSERT VALUES 'lastName' = 'Петров' , 'id'=1, 'age'=20, 'coast'=45.8, 'active'=false");
            System.out.println("INSERT OK");
            javaSchoolStarter.insert("INsERT VALUES 'lastName' = 'Романов' , 'id'=2, 'age'=36, 'coast'=20.4, 'active'=true");
            System.out.println("INSERT OK");
            javaSchoolStarter.insert("INSERT VAlUES 'lastName' = 'Грибоедов' , 'id'=3, 'age'=24, 'coast'=15.8, 'active'=false");
            System.out.println("INSERT OK");
            javaSchoolStarter.insert("INSERT VALUES 'lastName' = 'Толстой' , 'id'=4, 'age'=31, 'coast'=35.7, 'active'=true");
            System.out.println("INSERT OK");

            System.out.println(javaSchoolStarter.getResults("   SeLeCT where 'lastname' ilike 'в'  "));

            System.out.println(javaSchoolStarter.getResults("   SeLeCT where 'lastname' ilike 'в' and 'coast' >=16  "));

            System.out.println(javaSchoolStarter.getResults("   SeLeCT where 'coast' <= 16.5 or 'age' > 30  "));

            javaSchoolStarter.delete("   DELETE whEre id = 2   ");
            System.out.println("Delete Ok!");

            System.out.println(javaSchoolStarter.getResults("   SeLeCT where 'lastname' ilike 'в'  "));

            System.out.println(javaSchoolStarter.getResults("   SeLeCT "));


            javaSchoolStarter.delete("   DELETE whEre 'aCtivE' = false  ");
            System.out.println("Delete Ok!");

            System.out.println(javaSchoolStarter.getResults("   SeLeCT   "));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
