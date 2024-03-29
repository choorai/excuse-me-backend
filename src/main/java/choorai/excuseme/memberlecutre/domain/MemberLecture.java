package choorai.excuseme.memberlecutre.domain;

import choorai.excuseme.global.domain.BaseEntity;
import choorai.excuseme.lecture.domain.Lecture;
import choorai.excuseme.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberLecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_member"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_lecture"))
    private Lecture lecture;

    @Enumerated(EnumType.STRING)
    private ProgressStatus progressStatus;

    public MemberLecture(final Member member, final Lecture lecture) {
        this.member = member;
        this.lecture = lecture;
        this.progressStatus = ProgressStatus.NOT_WATCHED;
    }

    public void changeProgressStatus(final ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }
}
