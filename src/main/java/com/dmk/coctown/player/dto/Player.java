package com.dmk.coctown.player.dto;

import lombok.Getter;

@Getter
public class Player {

    private String tag; // 태그
    private String name; // 닉네임
    private Integer townHallLevel; // 마을 회관 레벨
    private Integer townHallWeaponLevel; // 마을 회관 무기 레벨
    private Integer expLevel; // 플레이어 레벨
    private Integer trophies; // 트로피 점수
    private Integer bestTrophies; // 마을회관 최고 트로피 점수
    private Integer warStars ; // 전쟁별 개수
    private Integer builderHallLevel ; // 장인회관 레벨
    private Integer builderBaseTrophies ; // 장인회관 트로피 점수
    private Integer bestBuilderBaseTrophies ; // 장인회관 최고 트로피 점수
    private String role; // 플레이어 역할 ENUM: [NOT_MEMBER, MEMBER, LEADER, ADMIN, COLEADER]
    private String warPreference; // 전쟁 선호도 ENUM: [IN, OUT]
    private Integer donations; // 지원병력 수
    private Integer donationsReceived; // 지원받은 병력의 수
    private Integer clanCapitalContributions; // 클랜 캐피탈 기여도



}
