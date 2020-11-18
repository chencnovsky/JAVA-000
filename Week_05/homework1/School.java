package java0.spring;

public class School implements ISchool {

    private IElement klass;

    private IPerson student;

    public IElement getKlass() {
        return klass;
    }

    public void setKlass(IElement klass) {
        this.klass = klass;
    }

    public IPerson getStudent() {
        return student;
    }

    public void setStudent(IPerson student) {
        this.student = student;
    }

    @Override
    public void build() {
        System.out.println("开始组建");
        klass.gen();
        student.gen();
    }
}
