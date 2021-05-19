# Azure Functions example in Scala

This is an example of how to use the Scala language for [Azure Functions](https://docs.microsoft.com/en-us/azure/azure-functions/).

## Getting Started

### Dependencies

* [Function Core Tools](https://aka.ms/azfunc-install)
* [Azure CLI](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli)

### Run this example
```shell script
./mvnw clean package azure-functions:run
```

## Deploy this sample on Azure

1. Sign up with your Azure Subscription:
    ```shell script
    az login
    az account set -s <your subscription id>
    ```

2. Make sure your function name is unique. To do that, rename your project from:
    ```xml
       <functionAppName>azure-scala-function-app-20210519152638152</functionAppName> 
    ```
   to:
   ```xml
       <functionAppName>azure-scala-function-app-%YOUR-NAME-HERE%></functionAppName>
   ```
   or any other unique string.
   

3. (Optionally) Find the following lines in the POM and configure your own deployment parameters 
such as *resource group*, *app service plan name*, *region*, *runtime*, etc.

    ```xml
      <plugin>
        <groupId>com.microsoft.azure</groupId>
        <artifactId>azure-functions-maven-plugin</artifactId>
        <version>${azure.functions.maven.plugin.version}</version>
        <configuration>
            <!-- function app name -->
            <appName>${functionAppName}</appName>
            <!-- function app resource group -->
            <resourceGroup>java-functions-group</resourceGroup>
            <!-- function app service plan name -->
            <appServicePlanName>java-functions-app-service-plan</appServicePlanName>
            <!-- function app region-->
            <!-- refers https://github.com/microsoft/azure-maven-plugins/wiki/Azure-Functions:-Configuration-Details#supported-regions for all valid values -->
            <region>westus</region>
            <!-- function pricingTier, default to be consumption if not specified -->
            <!-- refers https://github.com/microsoft/azure-maven-plugins/wiki/Azure-Functions:-Configuration-Details#supported-pricing-tiers for all valid values -->
            <!-- <pricingTier></pricingTier> -->
            <!-- Whether to disable application insights, default is false -->
            <!-- refers https://github.com/microsoft/azure-maven-plugins/wiki/Azure-Functions:-Configuration-Details for all valid configurations for application insights-->
            <!-- <disableAppInsights></disableAppInsights> -->
            <runtime>
                <!-- runtime os, could be windows, linux or docker-->
                <os>windows</os>
                <javaVersion>11</javaVersion>
            </runtime>
            <appSettings>
                <property>
                    <name>FUNCTIONS_EXTENSION_VERSION</name>
                    <value>~3</value>
                </property>
            </appSettings>
        </configuration>
        <executions>
            <execution>
                <id>package-functions</id>
                <goals>
                    <goal>package</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    ```

Run the following command to deploy this sample to an Azure Functions App:
```shell script
./mvnw clean package azure-functions:deploy
```

## Authors

* Aziret Satybaldiev [@etherealyn](https://twitter.com/etherealyn)

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.

## Acknowledgments

* [Deploying Scala code on Azure Functions by Montel Edwards](https://monteledwards.com/2018/10/07/deploying-scala-code-on-azure-functions/)
* [Azure Functions example in Java](https://github.com/Azure-Samples/azure-functions-samples-java)
