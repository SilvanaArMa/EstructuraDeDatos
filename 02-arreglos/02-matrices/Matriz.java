package 02-arreglos.02-matrices;

import javax.swing.JOptionPane;

public class Matriz {

    private int[][] datos;
    private int filas;
    private int columnas;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.datos = new int[filas][columnas];
    }

    public int getFilas() {
        return this.filas;
    }

    public int getColumnas() {
        return this.columnas;
    }

    public int getDato(int i, int j) {
        return this.datos[i][j];
    }

    public void setDato(int i, int j, int valor) {
        this.datos[i][j] = valor;
    }

    public void llenarMatriz() {
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                String input = JOptionPane.showInputDialog(null,
                        "Ingrese el valor para la posición [" + i + "][" + j + "]:");
                this.datos[i][j] = Integer.parseInt(input);
            }
        }
    }

    public String mostrarMatriz() {
        String salida = "Matriz actual (" + this.filas + "x" + this.columnas + "):\n";
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                salida += this.datos[i][j] + "\t";
            }
            salida += "\n";
        }
        return salida;
    }

    public void reservaAsiento(int fila, int col) {
        if (fila >= 0 && fila < this.filas && col >= 0 && col < this.columnas) {
            if (this.datos[fila][col] == 0) {
                this.datos[fila][col] = 1;
                JOptionPane.showMessageDialog(null, "¡Éxito! El asiento [" + fila + "][" + col + "] ha sido reservado.");
            } else {
                JOptionPane.showMessageDialog(null, "Aviso: El asiento [" + fila + "][" + col + "] ya está ocupado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Las coordenadas ingresadas exceden los límites de la matriz.");
        }
    }

    public int[] calcularSumaPorFilas() {
        int[] totalesSucursal = new int[this.filas];
        for (int i = 0; i < this.filas; i++) {
            int sumaFila = 0;
            for (int j = 0; j < this.columnas; j++) {
                sumaFila += this.datos[i][j];
            }
            totalesSucursal[i] = sumaFila;
        }
        return totalesSucursal;
    }

    public int[] calcularSumaPorColumnas() {
        int[] totalesMes = new int[this.columnas];
        for (int j = 0; j < this.columnas; j++) {
            int sumaColumna = 0;
            for (int i = 0; i < this.filas; i++) {
                sumaColumna += this.datos[i][j];
            }
            totalesMes[j] = sumaColumna;
        }
        return totalesMes;
    }

    public void generarReporteVentas() {
        int[] ventasSucursal = calcularSumaPorFilas();
        int[] ventasMes = calcularSumaPorColumnas();

        String reporte = "=== REPORTE ACUMULADO DE VENTAS ===\n\n--- Total por Sucursal (Filas) ---\n";
        for (int i = 0; i < ventasSucursal.length; i++) {
            reporte += "Sucursal " + (i + 1) + ": $" + ventasSucursal[i] + "\n";
        }

        reporte += "\n--- Total por Mes (Columnas) ---\n";
        for (int j = 0; j < ventasMes.length; j++) {
            reporte += "Mes " + (j + 1) + ": $" + ventasMes[j] + "\n";
        }

        JOptionPane.showMessageDialog(null, reporte);
    }

    public void diagnosticoMatrizIdentidad() {
        if (this.filas != this.columnas) {
            JOptionPane.showMessageDialog(null, "Advertencia: La operación se cancela. La matriz no es cuadrada (" + this.filas + "x" + this.columnas + ").");
            return;
        }

        boolean esIdentidad = true;
        int sumaDiagonal = 0;

        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                if (i == j) {
                    sumaDiagonal += this.datos[i][j];
                    if (this.datos[i][j] != 1) {
                        esIdentidad = false;
                    }
                } else {
                    if (this.datos[i][j] != 0) {
                        esIdentidad = false;
                    }
                }
            }
        }

        String resultado = "Diagnóstico de Matriz Cuadrada (" + this.filas + "x" + this.columnas + "):\n";
        resultado += "Suma de la diagonal principal: " + sumaDiagonal + "\n";
        if (esIdentidad) {
            resultado += "La matriz SÍ es una Matriz de Identidad.";
        } else {
            resultado += "La matriz NO es una Matriz de Identidad.";
        }

        JOptionPane.showMessageDialog(null, resultado);
    }

    public Matriz obtenerTranspuesta() {
        Matriz transpuesta = new Matriz(this.columnas, this.filas);
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                transpuesta.setDato(j, i, this.datos[i][j]);
            }
        }
        return transpuesta;
    }

    public Matriz rotar90Grados() {
        if (this.filas != this.columnas) {
            JOptionPane.showMessageDialog(null, "Advertencia: La matriz no es cuadrada, no se puede rotar 90°.");
            return null;
        }

        int n = this.filas;
        Matriz rotada = new Matriz(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotada.setDato(j, n - 1 - i, this.datos[i][j]);
            }
        }
        return rotada;
    }

    public static void iniciar() {
        String menu = "=== MENÚ PRINCIPAL DE MATRICES ===\n"
                + "1. Ejercicio 1: Reserva de Asientos\n"
                + "2. Ejercicio 2: Acumulado de Ventas por Sucursal/Mes\n"
                + "3. Ejercicio 3: Diagnóstico de Matriz de Identidad y Diagonal\n"
                + "4. Ejercicio 4: Transposición Dinámica (m*n -> n*m)\n"
                + "5. Ejercicio 5: Rotación de Matriz a 90° Horario\n"
                + "6. Salir\n\n"
                + "Seleccione una opción:";

        String input = JOptionPane.showInputDialog(null, menu);

        if (input == null) {
            return;
        }

        int opcion = Integer.parseInt(input);

        switch (opcion) {
            case 1: {
                int f = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de filas del teatro/sala:"));
                int c = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de columnas del teatro/sala:"));
                Matriz teatro = new Matriz(f, c);

                int rf = Integer.parseInt(JOptionPane.showInputDialog(teatro.mostrarMatriz() + "\nIngrese la fila del asiento a reservar:"));
                int rc = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la columna del asiento a reservar:"));
                teatro.reservaAsiento(rf, rc);
                JOptionPane.showMessageDialog(null, teatro.mostrarMatriz());
                break;
            }
            case 2: {
                int f = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de sucursales (filas):"));
                int c = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de meses (columnas):"));
                Matriz ventas = new Matriz(f, c);
                ventas.llenarMatriz();
                ventas.generarReporteVentas();
                break;
            }
            case 3: {
                int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la dimensión 'n' de la matriz cuadrada:"));
                Matriz cuadrada = new Matriz(n, n);
                cuadrada.llenarMatriz();
                cuadrada.diagnosticoMatrizIdentidad();
                break;
            }
            case 4: {
                int f = Integer.parseInt(JOptionPane.showInputDialog("Ingrese filas de la matriz original:"));
                int c = Integer.parseInt(JOptionPane.showInputDialog("Ingrese columnas de la matriz original:"));
                Matriz original = new Matriz(f, c);
                original.llenarMatriz();

                Matriz transpuesta = original.obtenerTranspuesta();
                JOptionPane.showMessageDialog(null, "--- MATRIZ ORIGINAL ---\n" + original.mostrarMatriz() +
                        "\n--- MATRIZ TRANSPUESTA (n x m) ---\n" + transpuesta.mostrarMatriz());
                break;
            }
            case 5: {
                int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la dimensión 'n' de la matriz cuadrada a rotar:"));
                Matriz mapa = new Matriz(n, n);
                mapa.llenarMatriz();

                Matriz rotada = mapa.rotar90Grados();
                if (rotada != null) {
                    JOptionPane.showMessageDialog(null, "--- MAPA ORIGINAL ---\n" + mapa.mostrarMatriz() +
                            "\n--- MAPA ROTADO 90° HORARIO ---\n" + rotada.mostrarMatriz());
                }
                break;
            }
            case 6:
                JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                return;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
                break;
        }

        iniciar();
    }
}