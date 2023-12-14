package kr.kuooe.Vopet.user.service.vo;

import lombok.Data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "vopet_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Long userSeq;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_mail")
    private String userMail;

    @Column(name = "user_rank")
    private String userRank;

    @Column(name = "user_rank_exdate")
    private Date userRankExDate;

    @Column(name = "user_classes_exdate")
    private Date userClassesExDate;

    @Column(name = "user_classes")
    private String userClasses;

    @Column(name = "user_regdate")
    private Date userRegDate;

    @Column(name = "user_update")
    private Date userUpdate;

    // Constructors
}