package in.ols.rest.service;

import in.ols.rest.model.Session;

public interface ISessionService {

	/**
	 * Checks if the session referred by apiToken exists and if its valid increases
	 * the session validity by further 60 seconds. Returns true/false accordingly
	 */
	public boolean isValidSession(String apiToken);

	public void incrementSessionValidity(String apiToken);

	public Session startNewSession(String username, String userType);
}
