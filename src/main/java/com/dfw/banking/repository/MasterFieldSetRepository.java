package com.dfw.banking.repository;

import java.util.List;
import java.util.Map;

public interface MasterFieldSetRepository {
	List<Map<String, Object>> getResponse(String fieldId);
	List<Map<String, Object>> getAllResponse();
}
