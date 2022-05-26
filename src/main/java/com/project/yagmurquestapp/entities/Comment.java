package com.project.yagmurquestapp.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comment") //

public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch=FetchType.LAZY) //birden fazla post işlemini bir kişi yapmış olabilir //FetchType.LAZY post objesini veritabanından çekerken user objesini görmememizi sağlar.Yeni post u çektiğimde ilgili user objesi bana gelmicek
    @JoinColumn(name="post_id",nullable = false)//bağlandığımızı belirtmiş oluruz
    @OnDelete(action = OnDeleteAction.CASCADE)    //bir kisi silindiğinde onun tüm postları silinmeli
    @JsonIgnore//serializeble için gerekli
    Post post;



    @ManyToOne(fetch=FetchType.LAZY) //birden fazla post işlemini bir kişi yapmış olabilir //FetchType.LAZY post objesini veritabanından çekerken user objesini görmememizi sağlar.Yeni post u çektiğimde ilgili user objesi bana gelmicek
    @JoinColumn(name="user_id",nullable = false)//bağlandığımızı belirtmiş oluruz
    @OnDelete(action = OnDeleteAction.CASCADE)    //bir kisi silindiğinde onun tüm postları silinmeli
    @JsonIgnore//serializeble için gerekli
    Kisi kisi;


    @Lob
    @Column (columnDefinition = "text")
    String text;
//    @Id
//    @Column(name="id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment")
//    @SequenceGenerator(name = "comment", allocationSize = 1, sequenceName = "seq_comment")
//    private Long id;
//
//    @Column(name="post_id")
//   // @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
//    private Long postId;
//
//    @Column(name="password", length = 150)//hizmet tür açıklama
//    @NotNull //boş geçilmesin//hiç bir şey gelmemesidir
//    private String password;
}
