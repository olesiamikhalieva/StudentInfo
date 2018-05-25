import service.ScriptReaderService;

public class Main {

    private static ScriptReaderService scriptReaderService;

    public static void main(String[] args) {
    scriptReaderService.insertToTableStudentScript30students();
    scriptReaderService.printStudents();
    scriptReaderService.printStudentWithEmail("gmail.com");
    scriptReaderService.printTheSameSernameStudent();
    scriptReaderService.insertToTableInfoScript();
    }


}