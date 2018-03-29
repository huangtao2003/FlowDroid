package soot.jimple.infoflow.entryPointCreators.android.components;

import soot.SootClass;
import soot.jimple.Jimple;
import soot.jimple.NopStmt;
import soot.jimple.Stmt;
import soot.jimple.infoflow.android.entryPointCreators.AndroidEntryPointConstants;

/**
 * Entry point creator for Android service connections
 * 
 * @author Steven Arzt
 *
 */
public class ServiceConnectionEntryPointCreator extends AbstractComponentEntryPointCreator {

	public ServiceConnectionEntryPointCreator(SootClass component, SootClass applicationClass) {
		super(component, applicationClass);
	}

	@Override
	protected void generateComponentLifecycle() {
		Stmt onServiceConnectedStmt = searchAndBuildMethod(
				AndroidEntryPointConstants.SERVICECONNECTION_ONSERVICECONNECTED, component, thisLocal);
		body.getUnits().add(onServiceConnectedStmt);

		// methods
		NopStmt startWhileStmt = Jimple.v().newNopStmt();
		NopStmt endWhileStmt = Jimple.v().newNopStmt();
		body.getUnits().add(startWhileStmt);
		createIfStmt(endWhileStmt);
		addCallbackMethods();
		body.getUnits().add(endWhileStmt);
		createIfStmt(startWhileStmt);

		Stmt onServiceDisconnectedStmt = searchAndBuildMethod(
				AndroidEntryPointConstants.SERVICECONNECTION_ONSERVICEDISCONNECTED, component, thisLocal);
		body.getUnits().add(onServiceDisconnectedStmt);
	}

}
