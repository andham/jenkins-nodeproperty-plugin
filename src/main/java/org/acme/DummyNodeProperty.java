package org.acme;

import java.io.IOException;
import java.util.logging.Logger;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Environment;
import hudson.model.Node;
import hudson.slaves.NodeProperty;
import hudson.slaves.NodePropertyDescriptor;

public class DummyNodeProperty extends NodeProperty<Node> {

    private static final Logger LOGGER = Logger.getLogger(DummyNodeProperty.class.getName());
    
    @DataBoundConstructor
    public DummyNodeProperty() {
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Environment setUp(AbstractBuild build, Launcher launcher, BuildListener listener)
            throws IOException, InterruptedException {
        LOGGER.info("In DummyNodeProperty setUp()");
        listener.getLogger().println("In DummyNodeProperty setUp()");
        return new Environment() {

            @Override
            public boolean tearDown(AbstractBuild build, BuildListener listener) {
                LOGGER.info("In DummyNodeProperty Environment tearDown()");
                listener.getLogger().println("In DummyNodeProperty Environmment tearDown()");
                return true;
            }
        };
    }
    
    @Extension
    @Symbol("dummynodeproperty")
    public static final class DescriptorImpl extends NodePropertyDescriptor {        
        @Override
        public String getDisplayName() {
            return "Dummy Node Property";
        }
    }

}
