package shop.onekorea.spring_board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PopularSearchEntity")
@Table(name = "popular_search")
public class PopularSearchEntity {
    @Id
    private String term;
    private int SearchCount;
    private String updated;
}