package top.ug666.ug.tools.bean;

import lombok.Data;

import java.util.List;

@Data
public class ContributorInfo {
    private List<Contributor> contributorList;

    @Data
    public static class Contributor {
        private String name;

        private String avatarUrl;

        private String link;
    }

}
