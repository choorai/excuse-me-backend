package choorai.excuseme.member.domain;

import choorai.excuseme.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(indexes = {
    @Index(name = "idx_username", columnList = "username", unique = true)
})
public class Member extends BaseEntity {

    private static final String DATE_FORMAT = "yyyyMMdd";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserName username;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Embedded
    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Member(final Long id,
                   final String username,
                   final String password,
                   final String name,
                   final String gender,
                   final String birthDate,
                   final String phoneNUmber,
                   final Role role
    ) {
        this.id = id;
        this.username = new UserName(username);
        this.password = password;
        this.name = name;
        this.gender = Gender.valueOf(gender);
        this.birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
        this.phoneNumber = new PhoneNumber(phoneNUmber);
        this.role = role;
    }

    public static Member createNormalMember(final String username,
                                            final String password,
                                            final String name,
                                            final String gender,
                                            final String birthDate,
                                            final String phoneNumber
    ) {
        return new Member(null, username, password, name, gender, birthDate, phoneNumber, Role.USER);
    }

    public static Member createAdminMember(final String username,
                                           final String password,
                                           final String name,
                                           final String gender,
                                           final String birthDate,
                                           final String phoneNUmber
    ) {
        return new Member(null, username, password, name, gender, birthDate, phoneNUmber, Role.ADMIN);
    }

    public String getUsername() {
        return username.getValue();
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }
}
