# PostCommands

PostCommands allows you to set up a REST server to remotely execute commands on 
the server as player or server. User creation isn't available as of yet.

## Usage

You can access the endpoint at <ip\>:<port\>/execute.  
By default ip is set to all available ip's and port 25580.

Example server request body looks like this:
```json
{
  "type": "SERVER",
  "command": "say Hello world"
}
```

Example player request body looks like this:
```json
{
  "type": "PLAYER",
  "playerName": "Notch",
  "command": "say Hello world"
}
```

## Authentication

For authentication to work, an Authorization header mu be set like this:
```
Authorization: Bearer <token>
```

## Configuration

On first startup a default user and master key will be generated.
User key must be created based on master key for validation to work.

Example default configuration (DO NOT COPY SECRET AND TOKEN FROM HERE):
```yml
host: 0.0.0.0
port: 25580
secret: 3QYYN4E6gzY_nds6qtwNt4O6QkLg6Vtzv8pvJhs4zOQ
users:
- ==: com.github.kaspiandev.postcommands.user.User
  user:
    ==: com.github.kaspiandev.postcommands.user.UserData
    name: admin
    permissions:
    - ==: com.github.kaspiandev.postcommands.permission.RequestTypePermission
      name: request-type
      request-type: SERVER
  token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiJ9.SRX2BvgdEFFZqaQhvgDMWDmprcNnEboO46xi5jkIgsM
```

## Permissions

Request Type:
```yml
- ==: com.github.kaspiandev.postcommands.permission.RequestTypePermission
  name: request-type
  request-type: SERVER
```

Command Permission:
```yml
- ==: com.github.kaspiandev.postcommands.permission.CommandPermission
  name: command
  command: "say Hello world"
  strict: true
```

If strict is set to true the command must be equal to what's set in command property.
When it's set to false the command has to start with what's set in command property.

## Building:

```sh
git clone https://github.com/KaspianDev/PostCommands.git
```

```sh
./gradlew build
```

Artifact will be located in build/libs.
