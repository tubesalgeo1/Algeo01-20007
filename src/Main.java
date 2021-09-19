import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("*********************WELCOME*********************");
        boolean isFinish = false;

        while (isFinish == false) {
            Utils.displayMenu();
            int choiceMenu = Utils.chooseOptionValidation(1, 6);

            // Sistem Persamaan Linier
            if (choiceMenu == 1) {
                float[][] matriks = ReadDisplayArray.readInput(true);
                ReadDisplayArray.displayOutput(matriks);
            }

            // Determinan
            else if (choiceMenu == 2) {

            }

            // Matriks Balikan
            else if (choiceMenu == 3) {

            }

            // Interpolasi Polinom
            else if (choiceMenu == 4) {

            }

            // Regresi Linier
            else if (choiceMenu == 5) {

            }

            // Keluar
            else if (choiceMenu == 6) {
                isFinish = true;
                System.out.println("Terima kasih!! Sampai jumpa lagiiiii");
            }

            if (isFinish == false) {
                isFinish = Utils.goToMenu();
            }
        }
    }
}
