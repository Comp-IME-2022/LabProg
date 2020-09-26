/**
 * IME 2022 - LabProg II
 *
 *    The goal of this code is to implement a FILO stack with Generics
 *    Functionalities:
 *        -Print
 *        -Push
 *        -Pop
 */

class Global {
    public static int max = 10;
    public static String full = "The array is full!";
    public static String empty = "The array is empty!";
}

public class Generics {
    public static void main(String[] args) {
        PilhaInteiro intPilha = new PilhaInteiro();
        PilhaPontoFlutuante doublePilha = new PilhaPontoFlutuante();
        PilhaCaractere charPilha = new PilhaCaractere();

        Integer arr[] = new Integer[10];
        Pilha<Integer> pilha = new Pilha<Integer>(arr);

        //Left some tests here to makes life easier :)
        intPilha.push(1);
        doublePilha.push(1.0);
        charPilha.push('a');

        intPilha.print();
        doublePilha.print();
        charPilha.print();

        pilha.push(1);
        pilha.print();
        pilha.pop();
        pilha.print();
    }
}

class PilhaInteiro {
    private Integer vector[] = new Integer[Global.max];
    private int nElem = 0;

    public void push(int var){
        if(nElem == 10){
            System.out.println(Global.full);
        }else{
           for(int i = nElem; i>0; i--){
               vector[i] = vector[i - 1];
           }
           vector[0] = var;
           nElem++;
        }
    }
    
    public void pop(){
        if(nElem == 0){
            System.out.println(Global.empty);
        }else{
            for(int i = 0; i<(nElem - 1); i++){
                vector[i] = vector[i + 1];
            }
            vector[nElem - 1] = null;
            nElem--;
        }
    }

    public void print(){
        if(nElem > 0){
            for(int i=0; i<(nElem - 1); i++){
                System.out.print(vector[i]+", ");
            }
            System.out.println(vector[nElem-1]);
        }else{
            System.out.println(Global.empty);
        }
    }
}

class PilhaPontoFlutuante {
    private Double vector[] = new Double[Global.max];
    private int nElem = 0;

    public void push(Double var){
        if(nElem == 10){
            System.out.println(Global.full);
        }else{
           for(int i = nElem; i>0; i--){
               vector[i] = vector[i - 1];
           }
           vector[0] = var;
           nElem++;
        }
    }
    
    public void pop(){
        if(nElem == 0){
            System.out.println(Global.empty);
        }else{
            for(int i = 0; i<(nElem - 1); i++){
                vector[i] = vector[i + 1];
            }
            vector[nElem - 1] = null;
            nElem--;
        }
    }

    public void print(){
        if(nElem > 0){
            for(int i=0; i<(nElem - 1); i++){
                System.out.print(vector[i]+", ");
            }
            System.out.println(vector[nElem-1]);
        }else{
            System.out.println(Global.empty);
        }
    }
}

class PilhaCaractere {
    private Character vector[] = new Character[Global.max];
    private int nElem = 0;

    public void push(Character var){
        if(nElem == 10){
            System.out.println(Global.full);
        }else{
           for(int i = nElem; i>0; i--){
               vector[i] = vector[i - 1];
           }
           vector[0] = var;
           nElem++;
        }
    }
    
    public void pop(){
        if(nElem == 0){
            System.out.println(Global.empty);
        }else{
            for(int i = 0; i<(nElem - 1); i++){
                vector[i] = vector[i + 1];
            }
            vector[nElem - 1] = null;
            nElem--;
        }
    }

    public void print(){
        if(nElem > 0){
            for(int i=0; i<(nElem - 1); i++){
                System.out.print(vector[i]+", ");
            }
            System.out.println(vector[nElem-1]);
        }else{
            System.out.println(Global.empty);
        }
    }
}

class Pilha<T> {
    private T vector[];
    private int nElem = 0;

    Pilha(T inputVector[]){
        vector = inputVector;
    }

    public void push(T var){
        if(nElem == 10){
            System.out.println(Global.full);
        }else{
           for(int i = nElem; i>0; i--){
               vector[i] = vector[i - 1];
           }
           vector[0] = var;
           nElem++;
        }
    }
    
    public void pop(){
        if(nElem == 0){
            System.out.println(Global.empty);
        }else{
            for(int i = 0; i<(nElem - 1); i++){
                vector[i] = vector[i + 1];
            }
            vector[nElem - 1] = null;
            nElem--;
        }
    }

    public void print(){
        if(nElem > 0){
            for(int i=0; i<(nElem - 1); i++){
                System.out.print(vector[i]+", ");
            }
            System.out.println(vector[nElem-1]);
        }else{
            System.out.println(Global.empty);
        }
    }
}