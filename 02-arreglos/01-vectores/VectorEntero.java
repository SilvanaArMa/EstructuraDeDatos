package 01-Conceptos_basicos.01-tda;

public class VectorEntero {

import javax.swing.JOptionPane;

public class VectorEntero {

    private int[] vector;
    private int tamanio;

    public VectorEntero(int tamanio) {
        this.tamanio = tamanio;
        this.vector = new int[tamanio];
    }

    public int[] getVector() {
        return vector;
    }

    public void agregarValor(int posicion, int valor) {
        if (posicion >= 0 && posicion < tamanio) {
            vector[posicion] = valor;
            JOptionPane.showMessageDialog(null, "Valor asignado correctamente en la posición " + posicion);
        } else {
            JOptionPane.showMessageDialog(null, "Posición fuera de rango.");
        }
    }

    public void llenarVector() {
        for (int i = 0; i < tamanio; i++) {
            String input = JOptionPane.showInputDialog("Ingrese el valor para el índice [" + i + "]:");
            if (input != null) {
                vector[i] = Integer.parseInt(input);
            }
        }
        JOptionPane.showMessageDialog(null, "Vector llenado completamente.");
    }

    public int sumarDatos() {
        int suma = 0;
        for (int num : vector) {
            suma += num;
        }
        return suma;
    }

    public int[] sumarConOtroVector(int[] otroVector) {
        if (otroVector.length != this.tamanio) {
            JOptionPane.showMessageDialog(null, "Los vectores deben ser del mismo tamaño.");
            return null;
        }
        int[] resultado = new int[tamanio];
        for (int i = 0; i < tamanio; i++) {
            resultado[i] = this.vector[i] + otroVector[i];
        }
        return resultado;
    }

    public int[] obtenerPares() {
        int contPares = 0;
        for (int num : vector) {
            if (num % 2 == 0) contPares++;
        }

        int[] pares = new int[contPares];
        int idx = 0;
        for (int num : vector) {
            if (num % 2 == 0) {
                pares[idx++] = num;
            }
        }
        return pares;
    }

    private boolean esPrimo(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public int[] obtenerPrimos() {
        int contPrimos = 0;
        for (int num : vector) {
            if (esPrimo(num)) contPrimos++;
        }

        int[] primos = new int[contPrimos];
        int idx = 0;
        for (int num : vector) {
            if (esPrimo(num)) {
                primos[idx++] = num;
            }
        }
        return primos;
    }

    public double calcularPromedio() {
        if (tamanio == 0) return 0;
        return (double) sumarDatos() / tamanio;
    }

    public int obtenerMayor() {
        int mayor = vector[0];
        for (int i = 1; i < tamanio; i++) {
            if (vector[i] > mayor) mayor = vector[i];
        }
        return mayor;
    }

    public int obtenerMenor() {
        int menor = vector[0];
        for (int i = 1; i < tamanio; i++) {
            if (vector[i] < menor) menor = vector[i];
        }
        return menor;
    }

    public String buscarElemento(int valor) {
        for (int i = 0; i < tamanio; i++) {
            if (vector[i] == valor) {
                return "Dato: " + valor + " | Posición (índice): " + i;
            }
        }
        return "El valor " + valor + " no se encuentra en el vector.";
    }

    public void mostrarVector() {
        StringBuilder sb = new StringBuilder("Vector: [ ");
        for (int num : vector) {
            sb.append(num).append(" ");
        }
        sb.append("]");
        JOptionPane.showMessageDialog(null, sb.toString());
    }
