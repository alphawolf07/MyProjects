import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
class Employee {
    private String name;
    private String id;
    private String deptCode;
    private double salary;
    private String grade;

    Employee() {
        name = "";
        id = "";
        deptCode = "";
        grade = "";
        salary = 0;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public double getSalary() {
        return salary;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\tName: " + name + "\nSalary: " + Double.toString(salary) + "\nDept. Code: " + deptCode
                + "\nGrade: " + grade + "\n";
    }

}

class RegisterEmployee extends JPanel {
    private Employee emp;
    private JTextField name, empId, salary;
    private JComboBox grades, deptCode;

    private ArrayList<Employee> empList;

    RegisterEmployee(ArrayList<Employee> empList) {
        emp = new Employee();
        this.empList = empList;
        setUpWindow();
    }

    public Employee getEmp() {
        return emp;
    }

    private void setUpWindow() {
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Name: "));
        name = new JTextField(5);
        add(name);

        add(new JLabel("Employee ID: "));
        empId = new JTextField(5);
        add(empId);

        add(new JLabel("Salary: "));
        salary = new JTextField(5);
        add(salary);

        add(new JLabel("Grade: "));
        String gradeList[] = { "A", "B", "C" };
        grades = new JComboBox<String>(gradeList);
        grades.setEditable(false);
        add(grades);

        add(new JLabel("Department: "));
        String deptList[] = { "BCSE", "BEEE", "IEEE", "ETCE", "FTBE" };
        deptCode = new JComboBox<String>(deptList);
        deptCode.setEditable(false);
        add(deptCode);

        grades.setSelectedIndex(0);
        deptCode.setSelectedIndex(0);

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetField();
            }
        });
        add(reset);

        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });
        add(save);

    }

    private void validateAndSetID() {
        String id = empId.getText();
        for (Employee e : empList) {
            if (e.getId().equals(id)) {
                JOptionPane.showMessageDialog(this.getParent(),
                        "Another employee " + e.getName() + " already exist with same ID!", "ID already exist",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        emp.setId(id);
    }

    private void saveData() {
        validateAndSetID();
        emp.setName(name.getText().trim());
        try {
            emp.setSalary(Double.parseDouble(salary.getText().trim()));
        } catch (Exception e) {
            salary.setText("");
            JOptionPane.showMessageDialog(this.getParent(), "Salary must be a number", "Salary",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        emp.setDeptCode((String) deptCode.getSelectedItem());
        emp.setGrade((String) grades.getSelectedItem());

        int opt = JOptionPane.showConfirmDialog(this.getParent(), "Save this employee data?", "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if(opt == JOptionPane.NO_OPTION) return;

        empList.add(emp);
        System.out.println(emp);

        resetField();
    }

    private void resetField() {
        emp = new Employee();
        name.setText("");
        salary.setText("0.0");
        empId.setText("");
        grades.setSelectedIndex(0);
        deptCode.setSelectedIndex(0);
    }
}
class ViewEmployee extends JPanel {
    private ArrayList<Employee> empList;
    private DefaultListModel<Employee> tempList;
    private JTextField searchBar;

    ViewEmployee(ArrayList<Employee> empList) {
        this.empList = empList;
        tempList = new DefaultListModel<>();

        setUpWindow();
    }

    private void setUpWindow() {
        setLayout(new BorderLayout());

        JButton viewAll = new JButton("View All");
        viewAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tempList.clear();
                tempList.addAll(empList);
                searchBar.setText("");
            }
        });

        JPanel searchPanel = new JPanel();
        searchBar = new JTextField(20);
        searchBar.setPreferredSize(new Dimension(20, 30));
        searchPanel.add(searchBar);
        JButton searchButton = new JButton(
                new ImageIcon(new ImageIcon("./search.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        searchButton.setPreferredSize(new Dimension(30, 30));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });
        viewAll.setPreferredSize(new Dimension(90, 30));
        searchPanel.add(searchButton);
        searchPanel.add(viewAll);
        add(searchPanel,BorderLayout.NORTH);

        JList<Employee> showList = new JList<Employee>(tempList);
        showList.setCellRenderer(new EmployeeRendere());
        add(new JScrollPane(showList), BorderLayout.CENTER);

    }

    private class EmployeeRendere extends JTextArea implements ListCellRenderer<Employee> {

        @Override
        public Component getListCellRendererComponent(JList<? extends Employee> list, Employee emp, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText(emp.toString());
            return this;
        }

    }

    private void handleSearch() {
        tempList.clear();
        for (Employee e : empList) {
            if (e.getId().equals(searchBar.getText())) {
                tempList.addElement(e);
                return;
            }
        }

        JOptionPane.showMessageDialog(this.getParent(), "No Employee Found", "Not Found",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

public class Assignment2_8 {
    private static ArrayList<Employee> empList;

    static {
        empList = new ArrayList<Employee>();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        JPanel panel = new JPanel(new CardLayout());
        JTabbedPane tabs = new JTabbedPane();

        RegisterEmployee regEmp = new RegisterEmployee(empList);
        tabs.addTab("Register Employee", regEmp);
        ViewEmployee viewEmp = new ViewEmployee(empList);
        tabs.addTab("View Employee", viewEmp);

        frame.add(tabs);

        frame.setVisible(true);

    }
}