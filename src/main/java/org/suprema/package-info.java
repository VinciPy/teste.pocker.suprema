@OpenAPIDefinition(
        info = @Info(
                title = "API of test (Suprema) Developer Backend",
                version = "0.2",
                description = "Poker Application Online - Tables and Players",
                contact = @Contact(name = "Vinicius Santana dos Anjos", email = "vinicius.santana.anjos@gmail.com")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Dev Enviroment"),
        }
)
package org.suprema;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;