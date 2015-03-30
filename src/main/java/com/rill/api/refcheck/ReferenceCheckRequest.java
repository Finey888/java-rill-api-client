package com.rill.api.refcheck;

import java.util.List;

public interface ReferenceCheckRequest {
    Candidate getCandidate();
    Employer getEmployer();
    List<Referral> getReferences();
}
