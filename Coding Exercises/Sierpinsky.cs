using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Sierpinsky : MonoBehaviour
{
    [SerializeField]
    public int maxDepth = 5;
    
    public bool changeColors = false;

    List<GameObject> triangles = new List<GameObject>();
    
    // Use this for initialization
    void Start()
    {
        DrawTriSierpinsky(0,maxDepth,new Vector2(-6,-4),new Vector2(0,4),new Vector2(6,-4));
    }

    void DrawTriSierpinsky(int level, int maxLevels, Vector2 l, Vector2 t, Vector2 r){
    	level++;
    	if (level > maxLevels) {
    		return;
  		}
  		CreateTriangle(l,t,r,-level);

        Vector2 a = l + (t - l)/2 ; 
        Vector2 b = r + (t - r)/2 ; 
        Vector2 c = l + (r - l)/2 ; 

  		DrawTriSierpinsky(level, maxLevels, a, t, b);
  		DrawTriSierpinsky(level, maxLevels, l, a, c);
  		DrawTriSierpinsky(level, maxLevels, c, b, r);

    }

    void CreateTriangle(Vector2 l, Vector2 t, Vector2 r,int layer){
        GameObject obj = new GameObject("name");
        obj.transform.parent = this.gameObject.transform;

        obj.AddComponent<MeshFilter>();
        obj.AddComponent<MeshRenderer>();
        Mesh mesh = obj.GetComponent<MeshFilter>().mesh;

        mesh.Clear();

        mesh.vertices = new Vector3[] {new Vector3(l.x, l.y, 0.01f*layer), new Vector3(t.x, t.y, 0.01f*layer), new Vector3(r.x, r.y, 0.01f*layer)};
        mesh.uv = new Vector2[] {new Vector2(0, 0), new Vector2(0, 1), new Vector2(1, 1)};
        mesh.triangles =  new int[] {0, 1, 2};

        Renderer rend = obj.GetComponent<Renderer> ();
        rend.material = new Material(Shader.Find("Custom/ColorShader"));

        AddRandomColorToMesh(mesh);

        triangles.Add(obj);
    }

    void AddRandomColorToMesh(Mesh mesh){
        Color[] colors = new Color[mesh.vertices.Length];

        Color randColor = new Color(Random.Range (0.0f, 1.0f),Random.Range (0.0f, 1.0f),Random.Range (0.0f, 1.0f),1.0f); 
        for (int i = 0; i < mesh.vertices.Length; i++){
            
            colors[i] = randColor;
        }

        mesh.colors = colors;
    }

    void Update(){
        if(changeColors){
            int rand = Random.Range(0,triangles.Count);
            Mesh mesh = triangles[rand].GetComponent<MeshFilter>().mesh;
            AddRandomColorToMesh(mesh);
        }
        
    }

}