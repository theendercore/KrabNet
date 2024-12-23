#version 150

in vec3 Position;
in vec4 Color;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform float GameTime;

out vec4 vertexColor;

void main() {
    // options
    float waveSize = 2.0; // determines wave size
    float waveHeight = 30.0; // height
    // calculation
    float timer = GameTime * 40000.0; // game time, determines amount of waves
    vec3 position = Position;
    float offset = sin(round(position.x / waveSize) + timer) - cos(round(position.z / waveSize) + timer); // calculate offset
    position = position + vec3(0.0, offset / waveHeight - 0.025, 0.0);

    gl_Position = ProjMat * ModelViewMat * vec4(position, 1.0) ;

    vertexColor = Color;
}
