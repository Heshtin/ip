# Augustus

Greetings, citizen.

Augustus is an **imperial-themed task manager** for keeping your duties,
deadlines, and events under control. Rome was not built in a day, and
neither is your assignment — so you may as well organise it.

Issue commands, organise your tasks, and let Augustus keep the empire —
or at least your schedule — in order.

## Setting up in IntelliJ IDEA

1. Ensure that **Java 25** is installed on your computer.
2. Clone this repository to your computer.
3. Open **IntelliJ IDEA**.
4. Select **Open** and choose the root folder of this project.
5. Allow IntelliJ to import the Gradle project and download the required
   dependencies.
6. Ensure that the project SDK is set to **Java 25**.
7. Locate:

   `src/main/java/augustus/Launcher.java`

8. Right-click `Launcher.java` and select **Run 'Launcher.main()'**.
9. The Augustus GUI should open.

Alternatively, you can run the application from the terminal:

```bash
./gradlew run
