package com.ivv;

import java.util.Scanner;

public class HW6_02 {
    public static void main(String[] args) {
        System.out.println("Введите размерность квадрата");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] magicSquare;
        if (n % 2 != 0) {
            magicSquare = generateOddSizedSquare(n);
        } else if (n % 4 == 0) {
            magicSquare = generateDoublyEvenSizedSquare(n);
        } else {
            magicSquare = generateSinglyEvenSizedSquare(n);
        }
        checkMagicSquare(magicSquare);
    }

    private static int[][] generateSinglyEvenSizedSquare(int n) {
        int size = n * n;
        int halfN = n / 2;
        int subSquareSize = size / 4;
        int[][] subSquare = generateOddSizedSquare(halfN);
        int[] quadrantFactors = {0, 2, 3, 1};
        int[][] magicSquare = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int quadrant = (r / halfN) * 2 + (c / halfN);
                magicSquare[r][c] = subSquare[r % halfN][c % halfN];
                magicSquare[r][c] += quadrantFactors[quadrant] * subSquareSize;
            }
        }
        int nColsLeft = halfN / 2;
        int nColsRight = nColsLeft - 1;
        for (int r = 0; r < halfN; r++) {
            for (int c = 0; c < n; c++) {
                if (c < nColsLeft || c >= n - nColsRight
                        || (c == nColsLeft && r == nColsLeft)) {
                    if (c == 0 && r == nColsLeft) {
                        continue;
                    }
                    int tmp = magicSquare[r][c];
                    magicSquare[r][c] = magicSquare[r + halfN][c];
                    magicSquare[r + halfN][c] = tmp;
                }
            }
        }
        return magicSquare;
    }

    private static int[][] generateDoublyEvenSizedSquare(int n) {
        int[][] magicSquare = new int[n][n];
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                magicSquare[i][j] = (n * i) + j + 1;
            }
        }
        for (i = 0; i < n / 4; i++) {
            for (j = 0; j < n / 4; j++) {
                magicSquare[i][j] = (n * n + 1) - magicSquare[i][j];
            }
        }
        for (i = 0; i < n / 4; i++) {
            for (j = 3 * (n / 4); j < n; j++) {
                magicSquare[i][j] = (n * n + 1) - magicSquare[i][j];
            }
        }
        for (i = 3 * n / 4; i < n; i++) {
            for (j = 0; j < n / 4; j++) {
                magicSquare[i][j] = (n * n + 1) - magicSquare[i][j];
            }
        }
        for (i = 3 * n / 4; i < n; i++) {
            for (j = 3 * n / 4; j < n; j++) {
                magicSquare[i][j] = (n * n + 1) - magicSquare[i][j];
            }
        }
        for (i = n / 4; i < 3 * n / 4; i++) {
            for (j = n / 4; j < 3 * n / 4; j++) {
                magicSquare[i][j] = (n * n + 1) - magicSquare[i][j];
            }
        }
        return magicSquare;
    }

    static int[][] generateOddSizedSquare(int n) {
        int[][] magicSquare = new int[n][n];
        int i = n / 2;
        int j = n - 1;
        for (int num = 1; num <= n * n; ) {
            if (i == -1 && j == n) {
                j = n - 2;
                i = 0;
            } else {
                if (j == n) {
                    j = 0;
                }
                if (i < 0) {
                    i = n - 1;
                }
            }
            if (magicSquare[i][j] != 0) {
                j -= 2;
                i++;
                continue;
            } else {
                magicSquare[i][j] = num++;
            }
            j++;
            i--;
        }
        return magicSquare;
    }

    static void checkMagicSquare(int[][] magicSquare) {
        int n = magicSquare[1].length;
        int sum=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(magicSquare[i][j] + "     ");
                sum+=magicSquare[i][j];
            }
            System.out.println(" - - сумма по строке = "+sum);
            sum=0;
        }
        System.out.println(" - - суммы по столбцам: ");
        for (int col = 0; col < n; col++) {
            for (int raw = 0; raw < n; raw++) {
                sum+=magicSquare[raw][col];
            }
            System.out.print(sum+" - ");
            sum=0;
        }
        System.out.println("\b\b");
        System.out.print("Cуммы по диагоналям: ");
        for (int col = 0; col < n; col++) {
            for (int raw = 0; raw < n; raw++) {
                if(raw==col) {
                    sum+=magicSquare[raw][col];}
            }
        }
        System.out.print(sum+" - ");
        sum=0;
        for (int col = n; col >= 0; col--) {
            for (int raw = 0; raw < n; raw++) {
                if(raw==col) {
                    sum+=magicSquare[raw][col];}
            }
        }
        System.out.print(sum);
    }
}