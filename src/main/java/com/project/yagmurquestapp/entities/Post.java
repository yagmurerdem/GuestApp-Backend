package com.project.yagmurquestapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
   // Long kisiId;

    @ManyToOne(fetch=FetchType.EAGER) //birden fazla post işlemini bir kişi yapmış olabilir //FetchType.LAZY post objesini veritabanından çekerken user objesini görmememizi sağlar(EAGER gösterir).Yeni post u çektiğimde ilgili user objesi bana gelmicek
    @JoinColumn(name="user_id",nullable = false)//bağlandığımızı belirtmiş oluruz
    @OnDelete(action = OnDeleteAction.CASCADE)    //bir kisi silindiğinde onun tüm postları silinmeli
 //   @JsonIgnore//serializeble için gerekli
    Kisi kisi;

    String title;
    @Lob
    @Column(columnDefinition="text")
    String text;
}
