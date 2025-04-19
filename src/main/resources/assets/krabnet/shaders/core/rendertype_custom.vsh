#version 150

in vec3 Position;
in vec4 Color;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform float GameTime;

out vec4 vertexColor;

void main() {
    // options
    float waveSize = 20.0;// determines wave size
    float waveHeight = 30.0;// height
    // calculation
    float timer = GameTime * 1000.0;// game time, determines amount of waves
    vec3 position = Position;
    float offsetx = sin(round(position.y / waveSize) + timer) - cos(round(position.z / waveSize) + timer);// calculate offset
    float offsetz = sin(round(position.y / waveSize) + timer) - cos(round(position.x / waveSize) + timer);// calculate offset
    position = position + vec3(-1*(offsetx / waveHeight - 0.025), 0.0, offsetz / waveHeight - 0.025);

    gl_Position = ProjMat * ModelViewMat * vec4(position, 1.0);

    vertexColor = Color;
}
