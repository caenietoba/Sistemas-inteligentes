package cube;

public class Piezas {
    public Central piezaCentral[];
    public Lateral piezaLateral[];
    public Esquina piezaEsquina[];

    public Piezas() {
        piezaCentral = new Central[6];
        for (int i = 0; i < piezaCentral.length; i++) {
            piezaCentral[i] = new Central();
        }
        piezaLateral = new Lateral[12];
        for (int i = 0; i < piezaLateral.length; i++) {
            piezaLateral[i] = new Lateral();
        }
        piezaEsquina = new Esquina[8];
        for (int i = 0; i < piezaEsquina.length; i++) {
            piezaEsquina[i] = new Esquina();
        }
    }

    public char quienCentral(char orient) {
        boolean esta = false;
        char color = ' ';
        int i = 0;
        int j = 0;
        while (i < piezaCentral.length && !esta) {
            j = 0;
            while (j < piezaCentral[i].tupla.orien.length && !esta) {
                if (piezaCentral[i].tupla.orien[j] == orient) {
                    esta = true;
                    color = piezaCentral[i].tupla.color[j];
                } else
                    j++;
            }
            i++;
        }
        return color;
    }

    public char quienLateral(char orient1, char orient2, char orientColor) {
        boolean esta = false;
        boolean pos1 = false;
        boolean pos2 = false;
        int i = 0;
        int j = 0;
        int k = 0;
        char color = ' ';
        while (i < piezaLateral.length && !esta) {
            j = 0;
            while (j < piezaLateral[i].tupla.orien.length && !pos1) {
                if (piezaLateral[i].tupla.orien[j] == orient1) {
                    pos1 = true;
                } else
                    j++;
            }
            k = 0;
            while (k < piezaLateral[i].tupla.orien.length && !pos2) {
                if (piezaLateral[i].tupla.orien[k] == orient2) {
                    pos2 = true;
                } else
                    k++;
            }
            if (pos1 && pos2) {
                if (orient1 == orientColor)
                    color = piezaLateral[i].tupla.color[j];
                else
                    color = piezaLateral[i].tupla.color[k];
                esta = true;
            } else {
                pos1 = false;
                pos2 = false;
                i++;
            }
        }
        return color;
    }

    public char quienEsquina(char orient1, char orient2, char orient3, char orientColor) {
        boolean esta = false;
        boolean pos1 = false;
        boolean pos2 = false;
        boolean pos3 = false;
        int i = 0, j = 0, k = 0, l = 0;
        char color = ' ';

        while (i < piezaEsquina.length && !esta) {
            j = 0;
            while (j < piezaEsquina[i].tupla.orien.length && !pos1) {
                if (piezaEsquina[i].tupla.orien[j] == orient1) {
                    pos1 = true;
                } else
                    j++;
            }
            k = 0;
            while (k < piezaEsquina[i].tupla.orien.length && !pos2) {
                if (piezaEsquina[i].tupla.orien[k] == orient2) {
                    pos2 = true;
                } else
                    k++;
            }
            l = 0;
            while (l < piezaEsquina[i].tupla.orien.length && !pos3) {
                if (piezaEsquina[i].tupla.orien[l] == orient3) {
                    pos3 = true;
                } else
                    l++;
            }
            if (pos1 && pos2 && pos3) {
                if (orient1 == orientColor)
                    color = piezaEsquina[i].tupla.color[j];
                else if (orient2 == orientColor)
                    color = piezaEsquina[i].tupla.color[k];
                else
                    color = piezaEsquina[i].tupla.color[l];
                esta = true;
            } else {
                pos1 = false;
                pos2 = false;
                pos3 = false;
                i++;
            }
        }
        return color;
    }

    public void imprimePiezas() {
        System.out.println(" " + quienEsquina('U', 'L', 'B', 'U') + quienLateral('U', 'B', 'U')
                + quienEsquina('U', 'R', 'B', 'U'));
        System.out.println(" " + quienLateral('U', 'L', 'U') + quienCentral('U') + quienLateral('U', 'R', 'U'));
        System.out.println(" " + quienEsquina('U', 'F', 'L', 'U') + quienLateral('U', 'F', 'U')
                + quienEsquina('U', 'F', 'R', 'U'));
        System.out.print("" + quienEsquina('L', 'U', 'B', 'L') + quienLateral('L', 'U', 'L')
                + quienEsquina('L', 'U', 'F', 'L') + " ");
        System.out.print("" + quienEsquina('F', 'U', 'L', 'F') + quienLateral('F', 'U', 'F')
                + quienEsquina('F', 'U', 'R', 'F') + " ");
        System.out.print("" + quienEsquina('F', 'U', 'R', 'R') + quienLateral('R', 'U', 'R')
                + quienEsquina('R', 'U', 'B', 'R') + " ");
        System.out.println("" + quienEsquina('B', 'U', 'R', 'B') + quienLateral('B', 'U', 'B')
                + quienEsquina('B', 'U', 'L', 'B') + " ");
        System.out.print("" + quienLateral('L', 'B', 'L') + quienCentral('L') + quienLateral('L', 'F', 'L') + " ");
        System.out.print("" + quienLateral('L', 'F', 'F') + quienCentral('F') + quienLateral('F', 'R', 'F') + " ");
        System.out.print("" + quienLateral('F', 'R', 'R') + quienCentral('R') + quienLateral('R', 'B', 'R') + " ");

        System.out.println("" + quienLateral('R', 'B', 'B') + quienCentral('B') + quienLateral('L', 'B', 'B') + " ");
        System.out.print("" + quienEsquina('L', 'D', 'B', 'L') + quienLateral('L', 'D', 'L')
                + quienEsquina('L', 'D', 'F', 'L') + " ");
        System.out.print("" + quienEsquina('F', 'D', 'L', 'F') + quienLateral('F', 'D', 'F')
                + quienEsquina('F', 'D', 'R', 'F') + " ");
        System.out.print("" + quienEsquina('F', 'D', 'R', 'R') + quienLateral('R', 'D', 'R')
                + quienEsquina('R', 'D', 'B', 'R') + " ");
        System.out.println("" + quienEsquina('B', 'D', 'R', 'B') + quienLateral('B', 'D', 'B')
                + quienEsquina('B', 'D', 'L', 'B') + " ");
        System.out.println(" " + quienEsquina('D', 'L', 'F', 'D') + quienLateral('D', 'F', 'D')
                + quienEsquina('D', 'F', 'R', 'D'));
        System.out.println(" " + quienLateral('D', 'L', 'D') + quienCentral('D') + quienLateral('D', 'R', 'D'));
        System.out.println(" " + quienEsquina('D', 'L', 'B', 'D') + quienLateral('D', 'B', 'D')
                + quienEsquina('D', 'R', 'B', 'D'));
        System.out.println("");
    }

    public void iniciaPiezas() {
        piezaCentral[0].tupla.color[0] = 'B';
        piezaCentral[0].tupla.orien[0] = 'R';
        piezaCentral[1].tupla.color[0] = 'Y';
        piezaCentral[1].tupla.orien[0] = 'F';
        piezaCentral[2].tupla.color[0] = 'R';
        piezaCentral[2].tupla.orien[0] = 'U';
        piezaCentral[3].tupla.color[0] = 'G';
        piezaCentral[3].tupla.orien[0] = 'L';
        piezaCentral[4].tupla.color[0] = 'O';
        piezaCentral[4].tupla.orien[0] = 'D';
        piezaCentral[5].tupla.color[0] = 'W';
        piezaCentral[5].tupla.orien[0] = 'B';
        piezaLateral[0].tupla.color[0] = 'R';
        piezaLateral[0].tupla.orien[0] = 'U';
        piezaLateral[0].tupla.color[1] = 'Y';
        piezaLateral[0].tupla.orien[1] = 'F';
        piezaLateral[1].tupla.color[0] = 'B';
        piezaLateral[1].tupla.orien[0] = 'R';
        piezaLateral[1].tupla.color[1] = 'Y';
        piezaLateral[1].tupla.orien[1] = 'F';
        piezaLateral[2].tupla.color[0] = 'O';
        piezaLateral[2].tupla.orien[0] = 'D';
        piezaLateral[2].tupla.color[1] = 'Y';
        piezaLateral[2].tupla.orien[1] = 'F';
        piezaLateral[3].tupla.color[0] = 'G';
        piezaLateral[3].tupla.orien[0] = 'L';
        piezaLateral[3].tupla.color[1] = 'Y';
        piezaLateral[3].tupla.orien[1] = 'F';
        piezaLateral[4].tupla.color[0] = 'R';
        piezaLateral[4].tupla.orien[0] = 'U';
        piezaLateral[4].tupla.color[1] = 'B';
        piezaLateral[4].tupla.orien[1] = 'R';
        piezaLateral[5].tupla.color[0] = 'R';
        piezaLateral[5].tupla.orien[0] = 'U';
        piezaLateral[5].tupla.color[1] = 'G';
        piezaLateral[5].tupla.orien[1] = 'L';
        piezaLateral[6].tupla.color[0] = 'O';
        piezaLateral[6].tupla.orien[0] = 'D';
        piezaLateral[6].tupla.color[1] = 'B';
        piezaLateral[6].tupla.orien[1] = 'R';

        piezaLateral[7].tupla.color[0] = 'O';
        piezaLateral[7].tupla.orien[0] = 'D';
        piezaLateral[7].tupla.color[1] = 'G';
        piezaLateral[7].tupla.orien[1] = 'L';
        piezaLateral[8].tupla.color[0] = 'R';
        piezaLateral[8].tupla.orien[0] = 'U';
        piezaLateral[8].tupla.color[1] = 'W';
        piezaLateral[8].tupla.orien[1] = 'B';
        piezaLateral[9].tupla.color[0] = 'G';
        piezaLateral[9].tupla.orien[0] = 'L';
        piezaLateral[9].tupla.color[1] = 'W';
        piezaLateral[9].tupla.orien[1] = 'B';
        piezaLateral[10].tupla.color[0] = 'B';
        piezaLateral[10].tupla.orien[0] = 'R';
        piezaLateral[10].tupla.color[1] = 'W';
        piezaLateral[10].tupla.orien[1] = 'B';
        piezaLateral[11].tupla.color[0] = 'O';
        piezaLateral[11].tupla.orien[0] = 'D';
        piezaLateral[11].tupla.color[1] = 'W';
        piezaLateral[11].tupla.orien[1] = 'B';
        piezaEsquina[0].tupla.color[0] = 'R';
        piezaEsquina[0].tupla.orien[0] = 'U';
        piezaEsquina[0].tupla.color[1] = 'Y';
        piezaEsquina[0].tupla.orien[1] = 'F';
        piezaEsquina[0].tupla.color[2] = 'B';
        piezaEsquina[0].tupla.orien[2] = 'R';
        piezaEsquina[1].tupla.color[0] = 'R';
        piezaEsquina[1].tupla.orien[0] = 'U';
        piezaEsquina[1].tupla.color[1] = 'Y';
        piezaEsquina[1].tupla.orien[1] = 'F';
        piezaEsquina[1].tupla.color[2] = 'G';
        piezaEsquina[1].tupla.orien[2] = 'L';
        piezaEsquina[2].tupla.color[0] = 'O';
        piezaEsquina[2].tupla.orien[0] = 'D';
        piezaEsquina[2].tupla.color[1] = 'Y';
        piezaEsquina[2].tupla.orien[1] = 'F';
        piezaEsquina[2].tupla.color[2] = 'B';
        piezaEsquina[2].tupla.orien[2] = 'R';
        piezaEsquina[3].tupla.color[0] = 'O';
        piezaEsquina[3].tupla.orien[0] = 'D';
        piezaEsquina[3].tupla.color[1] = 'Y';
        piezaEsquina[3].tupla.orien[1] = 'F';
        piezaEsquina[3].tupla.color[2] = 'G';
        piezaEsquina[3].tupla.orien[2] = 'L';
        piezaEsquina[4].tupla.color[0] = 'R';
        piezaEsquina[4].tupla.orien[0] = 'U';
        piezaEsquina[4].tupla.color[1] = 'B';
        piezaEsquina[4].tupla.orien[1] = 'R';
        piezaEsquina[4].tupla.color[2] = 'W';
        piezaEsquina[4].tupla.orien[2] = 'B';
        piezaEsquina[5].tupla.color[0] = 'R';
        piezaEsquina[5].tupla.orien[0] = 'U';
        piezaEsquina[5].tupla.color[1] = 'G';
        piezaEsquina[5].tupla.orien[1] = 'L';
        piezaEsquina[5].tupla.color[2] = 'W';
        piezaEsquina[5].tupla.orien[2] = 'B';
        piezaEsquina[6].tupla.color[0] = 'O';
        piezaEsquina[6].tupla.orien[0] = 'D';
        piezaEsquina[6].tupla.color[1] = 'B';
        piezaEsquina[6].tupla.orien[1] = 'R';

        piezaEsquina[6].tupla.color[2] = 'W';
        piezaEsquina[6].tupla.orien[2] = 'B';
        piezaEsquina[7].tupla.color[0] = 'O';
        piezaEsquina[7].tupla.orien[0] = 'D';
        piezaEsquina[7].tupla.color[1] = 'G';
        piezaEsquina[7].tupla.orien[1] = 'L';
        piezaEsquina[7].tupla.color[2] = 'W';
        piezaEsquina[7].tupla.orien[2] = 'B';
    }

    public void movHorizontal(char cara, int signo) {
        char movsH[] = { 'F', 'L', 'B', 'R' };
        for (int i = 0; i < piezaLateral.length; i++) {
            for (int j = 0; j < piezaLateral[i].tupla.orien.length; j++) {
                if (piezaLateral[i].tupla.orien[j] == cara) {
                    for (int k = 0; k < piezaLateral[i].tupla.orien.length; k++) {
                        if (piezaLateral[i].tupla.orien[k] != cara) {
                            char caraActual = piezaLateral[i].tupla.orien[k];
                            int caraNueva = 0;
                            while (caraActual != movsH[caraNueva])
                                caraNueva++;
                            if (signo == 0)
                                caraNueva++;
                            else
                                caraNueva--;
                            if (caraNueva < 0)
                                caraNueva = 3;
                            else if (caraNueva > 3)
                                caraNueva = 0;
                            piezaLateral[i].tupla.orien[k] = movsH[caraNueva];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < piezaEsquina.length; i++) {
            for (int j = 0; j < piezaEsquina[i].tupla.orien.length; j++) {
                if (piezaEsquina[i].tupla.orien[j] == cara) {
                    for (int k = 0; k < piezaEsquina[i].tupla.orien.length; k++) {
                        if (piezaEsquina[i].tupla.orien[k] != cara) {
                            char caraActual = piezaEsquina[i].tupla.orien[k];
                            int caraNueva = 0;
                            while (caraActual != movsH[caraNueva])
                                caraNueva++;
                            if (signo == 0)
                                caraNueva++;
                            else
                                caraNueva--;
                            if (caraNueva < 0)
                                caraNueva = 3;
                            else if (caraNueva > 3)
                                caraNueva = 0;

                            piezaEsquina[i].tupla.orien[k] = movsH[caraNueva];
                        }
                    }
                }
            }
        }
    }
    
    public void movVertical(char cara, int signo) {
        char movsV[] = { 'F', 'U', 'B', 'D' };
        for (int i = 0; i < piezaLateral.length; i++) {
            for (int j = 0; j < piezaLateral[i].tupla.orien.length; j++) {
                if (piezaLateral[i].tupla.orien[j] == cara) {
                    for (int k = 0; k < piezaLateral[i].tupla.orien.length; k++) {
                        if (piezaLateral[i].tupla.orien[k] != cara) {
                            char caraActual = piezaLateral[i].tupla.orien[k];
                            int caraNueva = 0;
                            while (caraActual != movsV[caraNueva])
                                caraNueva++;
                            if (signo == 0)
                                caraNueva++;
                            else
                                caraNueva--;
                            if (caraNueva < 0)
                                caraNueva = 3;
                            else if (caraNueva > 3)
                                caraNueva = 0;
                            piezaLateral[i].tupla.orien[k] = movsV[caraNueva];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < piezaEsquina.length; i++) {
            for (int j = 0; j < piezaEsquina[i].tupla.orien.length; j++) {
                if (piezaEsquina[i].tupla.orien[j] == cara) {
                    for (int k = 0; k < piezaEsquina[i].tupla.orien.length; k++) {
                        if (piezaEsquina[i].tupla.orien[k] != cara) {
                            char caraActual = piezaEsquina[i].tupla.orien[k];
                            int caraNueva = 0;
                            while (caraActual != movsV[caraNueva])
                                caraNueva++;
                            if (signo == 0)
                                caraNueva++;
                            else
                                caraNueva--;
                            if (caraNueva < 0)
                                caraNueva = 3;
                            else if (caraNueva > 3)
                                caraNueva = 0;

                            piezaEsquina[i].tupla.orien[k] = movsV[caraNueva];
                        }
                    }
                }
            }
        }
    }
}