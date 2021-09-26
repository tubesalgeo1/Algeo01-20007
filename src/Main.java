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
                System.out.println("******************** SISTEM PERSAMAAN LINIER ********************");
            }

            // Determinan
            else if (choiceMenu == 2) {
                System.out.println("******************** DETERMINAN ********************");
                Determinan.determinan();
            }

            // Matriks Balikan
            else if (choiceMenu == 3) {
                System.out.println("******************** MATRIKS BALIKAN ********************");
            }

            // Interpolasi Polinom
            else if (choiceMenu == 4) {
                System.out.println("******************** INTERPOLASI POLINOM ********************");
                RegresiLinier.interpolasiPolinom();
            }

            // Regresi Linier
            else if (choiceMenu == 5) {
                System.out.println("******************** REGRESI LINIER ********************");
                RegresiLinier.regresiLinier();
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
