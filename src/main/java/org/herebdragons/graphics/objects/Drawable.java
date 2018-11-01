package org.herebdragons.graphics.objects;


import java.awt.RenderingHints;
import java.awt.Graphics2D;

public interface Drawable {

    enum notSoSimpleRenderingHints {
        ANTIALIASING_ON(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON),
        ANTIALIASING_OFF(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF),
        ANTIALIASING_DEFAULT(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT),

        ALPHA_INTERPOLATION_DEFAULT(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT),
        ALPHA_INTERPOLATION_SPEED(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED),
        ALPHA_INTERPOLATION_QUALITY(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY),

        COLOR_RENDERING_DEFAULT(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_DEFAULT),
        COLOR_RENDERING_QUALITY(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY),
        COLOR_RENDERING_SPEED(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED),

        DITHERING_DEFAULT(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DEFAULT),
        DITHERING_ENABLE(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE),
        DITHERING_DISABLE(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE),

        FRACTIONAL_METRICS_DEFAULT(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT),
        FRACTIONAL_METRICS_ON(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON),
        FRACTIONAL_METRICS_OFF(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF),

        INTERPOLATION_BICUBIC(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC),
        INTERPOLATION_BILINEAR(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR),
        INTERPOLATION_NEAREST_NEIGHBOR(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR),

        RENDERING_DEFAULT(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT),
        RENDERING_SPEED(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED),
        RENDERING_QUALITY(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY),

        STROKE_DEFAULT(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT),
        STROKE_NORMALIZE(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE),
        STROKE_PURE(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE),

        TEXT_ANTIALIAS_ON(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON),
        TEXT_ANTIALIAS_OFF(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF),
        TEXT_ANTIALIAS_DEFAULT(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);

        private RenderingHints hint;

        notSoSimpleRenderingHints(RenderingHints.Key key, Object value) {

            this.hint = new RenderingHints(key, value);

        }

        public RenderingHints getHint() {
            return hint;
        }

    }

    void render(Graphics2D g);

    void setRenderingHints(notSoSimpleRenderingHints[] hints);

    notSoSimpleRenderingHints[] getRenderingHints();
}
