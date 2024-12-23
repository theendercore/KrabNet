#version 150

in vec4 vertexColor;

uniform float GameTime;

out vec4 fragColor;

void main() {
    vec4 color = vertexColor;
    if (color.a == 0.0) {
        discard;
    }
    float x = sin(GameTime * 1000.);
    fragColor = vec4(x, x, x, 1.);//color * ColorModulator * GameTime;
}
