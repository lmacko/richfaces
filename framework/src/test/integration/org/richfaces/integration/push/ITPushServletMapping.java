/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.richfaces.integration.push;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.webapp30.WebAppDescriptor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.richfaces.deployment.FrameworkDeployment;
import org.richfaces.servlet.PushServlet;

import category.Smoke;

import com.google.common.base.Function;

@RunWith(Arquillian.class)
@WarpTest
@Category(Smoke.class)
@Ignore("RF-13290 test fails after upgrade to 1.0.17 (probably Warp issue)")
public class ITPushServletMapping extends AbstractPushTest {

    @Deployment
    public static WebArchive createDeployment() {
        FrameworkDeployment deployment = createBasicDeployment(ITPushServletMapping.class);

        deployment.webXml(new Function<WebAppDescriptor, WebAppDescriptor>() {
            public WebAppDescriptor apply(WebAppDescriptor webXml) {
                return webXml
                        .createServlet()
                            .servletName(PushServlet.class.getSimpleName())
                            .servletClass(PushServlet.class.getName())
                            .asyncSupported(true)
                        .up()
                        .createServletMapping()
                            .servletName(PushServlet.class.getSimpleName())
                            .urlPattern("/__custom_mapping")
                        .up()
                        .createContextParam()
                            .paramName("org.richfaces.push.handlerMapping")
                            .paramValue("/__custom_mapping")
                        .up();
            }
        });

        return deployment.getFinalArchive();
    }

    @Test
    @RunAsClient
    public void test() {
        super.testSimplePush();
    }
}
