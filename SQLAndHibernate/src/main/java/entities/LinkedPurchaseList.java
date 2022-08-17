package entities;

import ck.LinkedPurchaseListKey;
import jakarta.persistence.*;

@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListKey id;

    @Column(name = "course_id", nullable = false, insertable = false, updatable = false)
    private int courseId;

    @Column(name = "student_id", nullable = false, insertable = false, updatable = false)
    private int studentId;

    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudent_id() {
        return studentId;
    }

    public void setStudent_id(int student_id) {
        this.studentId = student_id;
    }

}
