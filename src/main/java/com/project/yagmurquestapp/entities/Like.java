package com.project.yagmurquestapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="p_like")
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch= FetchType.LAZY) //birden fazla post işlemini bir kişi yapmış olabilir //FetchType.LAZY post objesini veritabanından çekerken user objesini görmememizi sağlar.Yeni post u çektiğimde ilgili user objesi bana gelmicek
    @JoinColumn(name="post_id",nullable = false)//bağlandığımızı belirtmiş oluruz
    @OnDelete(action = OnDeleteAction.CASCADE)    //bir kisi silindiğinde onun tüm postları silinmeli
    @JsonIgnore//serializeble için gerekli
    Post post;



    @ManyToOne(fetch=FetchType.LAZY) //birden fazla post işlemini bir kişi yapmış olabilir //FetchType.LAZY post objesini veritabanından çekerken user objesini görmememizi sağlar.Yeni post u çektiğimde ilgili user objesi bana gelmicek
    @JoinColumn(name="user_id",nullable = false)//bağlandığımızı belirtmiş oluruz
    @OnDelete(action = OnDeleteAction.CASCADE)    //bir kisi silindiğinde onun tüm postları silinmeli
    @JsonIgnore//serializeble için gerekli
    Kisi kisi;
}
