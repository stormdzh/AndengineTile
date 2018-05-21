package com.stormdzh.tilemap;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLoader;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.layer.tiled.tmx.util.exception.TMXLoadException;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class MainActivity extends BaseGameActivity {

    private static final int CAMERA_WIDTH = 800;
    private static final int CAMERA_HEIGHT = 480;
    private Camera mCamera;
    protected Scene mMainScene;

    private TMXTiledMap mTMXMap;
    private TMXLayer mTMXLayer1;
    private TMXLayer mTMXLayer2;
    private TMXLayer mTMXLayer3;
    private TMXTile mTMXTile;

    @Override
    public Engine onLoadEngine() {
// TODO Auto-generated method stub
// TODO Auto-generated method stub
        this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

// TODO Auto-generated method stub
        return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
                this.mCamera));

    }

    @Override
    public void onLoadResources() {
// TODO Auto-generated method stub

    }

    @Override
    public Scene onLoadScene() {
// TODO Auto-generated method stub
        this.mEngine.registerUpdateHandler(new FPSLogger());
        mMainScene = new Scene(1);
        try {
            final TMXLoader tmxLoader = new TMXLoader(this,
                    this.mEngine.getTextureManager(),
                    TextureOptions.BILINEAR_PREMULTIPLYALPHA,
                    new ITMXTilePropertiesListener() {

                        @Override
                        public void onTMXTileWithPropertiesCreated(
                                TMXTiledMap pTMXTiledMap,
                                TMXLayer pTMXLayer,
                                TMXTile pTMXTile,
                                TMXProperties<TMXTileProperty> pTMXTileProperties) {
// TODO Auto-generated method stub

                        }
                    });
            this.mTMXMap = tmxLoader.loadFromAsset(this, "map/mymap.tmx");

        } catch (final TMXLoadException tmxle) {

        }
        mTMXLayer1 = this.mTMXMap.getTMXLayers().get(0);
        mMainScene.getFirstChild().attachChild(mTMXLayer1);
        mTMXLayer2 = this.mTMXMap.getTMXLayers().get(1);
        mMainScene.getFirstChild().attachChild(mTMXLayer2);

        mTMXLayer3 = this.mTMXMap.getTMXLayers().get(2);
        mMainScene.getFirstChild().attachChild(mTMXLayer3);
        mMainScene.setOnSceneTouchListener(new IOnSceneTouchListener() {

            @Override
            public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
// TODO Auto-generated method stub
                switch (pSceneTouchEvent.getAction()) {
                    case TouchEvent.ACTION_DOWN:
                        mTMXTile = mTMXLayer3.getTMXTileAt(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                        System.out.println("-------------------------qwe");
                        if (mTMXTile != null && (mTMXTile.getGlobalTileID() == 382)) {
                            mTMXTile.setGlobalTileID(mTMXMap, 390);

                        }
                        break;

                }
                return true;
            }
        });
        return mMainScene;
    }

    @Override
    public void onLoadComplete() {
// TODO Auto-generated method stub

    }

}
