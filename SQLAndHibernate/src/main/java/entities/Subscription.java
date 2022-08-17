package entities;

import ck.SubscriptionKey;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Instant subscriptionDate;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SubscriptionKey getId() {
        return id;
    }

    public void setId(SubscriptionKey id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instant getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Instant subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
