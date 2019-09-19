package app;

import cube.Piezas;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        Piezas cubo = new Piezas();
        Cube cube = new Cube();
        //Integer[][][] _cube = cube.init();
        cube.actions();
        //cubo.iniciaPiezas();
        //cubo.imprimePiezas();
        //cubo.movVertical('D', 0);
        //cubo.movHorizontal('D', 0);
        //cubo.imprimePiezas();
    }
}