package com.istc.Entities.PropertyInterface;

/**
 * Created by lurui on 2017/3/15 0015.
 */
public interface Authority {
    int rank0 = 0;
    int rank1 = 1;
    int rank2 = 2;
    int rank3 = 3;

    int intervieweeeAuthority = (1 << rank0) - 1;
    int memberAuthority = (1 << rank1) - 1;
    int ministerAuthority = (1 << rank2) - 1;
    int presidentAuthority = (1 << rank3) - 1;
}
