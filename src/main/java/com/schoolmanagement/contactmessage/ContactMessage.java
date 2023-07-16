package com.schoolmanagement.contactmessage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data//@Getter @Setter @toString @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
/*
@Entity: Bu anotasyon, bir sınıfın veritabanında bir tabloyu temsil ettiğini belirtir. Veri tabanı işlemleri için kullanılan
JPA (Java Persistence API) tarafından tanımlanan bir anotasyondur.
@Data: Bu anotasyon, Lombok kütüphanesine aittir ve sınıf için standart getter, setter, toString, equals ve hashCode metodlarını
 otomatik olarak oluşturur. Bu anotasyon sayesinde, sınıfın temel veri işleme işlevselliği otomatik olarak sağlanır.
@AllArgsConstructor: Bu anotasyon, Lombok tarafından sağlanan bir anotasyondur ve tüm sınıf alanları için bir constructor (kurucu) oluşturur.
Bu, tüm alanların parametre olarak kullanıldığı bir constructor oluşturur.
@NoArgsConstructor: Bu anotasyon, Lombok tarafından sağlanan bir anotasyondur ve hiçbir parametre almayan bir constructor (kurucu) oluşturur.
 Bu, sınıfın boş bir örneğini oluşturmanıza olanak tanır.
@Builder: Bu anotasyon, Lombok tarafından sağlanan bir anotasyondur ve Builder tasarım desenini uygulamak için kullanılır.
 Builder deseni, nesne oluşturma sürecini daha esnek hale getirir ve daha okunabilir bir yapı sunar.
  Bu anotasyonu kullanarak, nesneyi inşa etmek için zincirleme bir yapı kullanabilirsiniz.
 */
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//contactMessageId

    @NotNull
    private String name;//ContactMessageName

    @NotNull
    private String email;

    @NotNull
    private String subject;

    @NotNull
    private String message;

    //2025-06-05
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate date;
}
