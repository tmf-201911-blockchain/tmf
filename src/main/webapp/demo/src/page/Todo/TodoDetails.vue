<template>
  <div class="base-station">
    <div class="header-title">
      <div class="header-title-left">
        <div>
          <span style="font-size: 18px; font-weight: bold; color: black">{{ details.resourceName }}</span>
          <span style="font-size: 13px; color: #999999; margin-left: 10px">(Task ID：{{ details.taskId }})</span>
        </div>
        <div style="margin: 10px 0 20px 0;" v-if="!details.hideSome">
          <span style="font-size: 13px; color: #999999;">Applicant：{{ details.lessee }} </span>
          <span style="font-size: 13px; color: #999999; margin-left: 20px">Application Time：{{ details.applicationTime }} </span>
        </div>
      </div>

      <div class="header-title-right">
        <div>
          <div class="pending" v-if="details.progress == '0'">Pending</div>
          <div class="approval" v-if="details.progress == '2'">Approved</div>
          <div class="refuse" v-if="details.progress == '1'">Refuse</div>
          <div v-if="!details.hideSome">
            <p>Application type：<span>{{ this.applicationType[details.applicationType] }}</span></p>
          </div>
        </div>
      </div>
    </div>

    <div class="base-station-info">
      <div class="base-station-info-left">
        <div class="base-img">
          <img src="../../assets/photo@2x.png" alt="" style="width: 100%; height: 100%">
        </div>
        <div class="base-state">{{ this.status[details.status] }}</div>
      </div>

      <div class="base-station-info-right">
        <div>
          <span style="font-size: 18px; font-weight: bold; color: black">{{ details.resourceName }}</span>
          <span style="font-size: 13px; color: #999999; margin-left: 10px">(Resource ID：{{ details.resourceId }})</span>
        </div>
        <div style="font-size: 13px;">
          <span>Address：</span>
          <span>{{ details.address }}</span>
        </div>
        <div class="base-params">
          <div>
            <p>{{ details.resourceType }}</p>
            <p>Resource Type</p>
          </div>
          <div>
            <p>{{ this.importance[details.importance] }}</p>
            <p>Importance</p>
          </div>
          <div>
            <p>{{ details.areaCode }}</p>
            <p>Area Code</p>
          </div>
          <div>
            <p>{{ details.longitude }}°E</p>
            <p>Latitude</p>
          </div>
          <div>
            <p>{{ details.latitude }}°N</p>
            <p>Longitude</p>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
  import {mapState} from 'vuex';

  export default {
    name: "TodoDetails",
    data() {
      return {
        details: null,
        status: {
          0: 'Running',
          1: 'Constructing',
          2: 'Planning',
          3: 'Maintaining',
        },
        importance: {
          0: 'High',
          1: 'Middle',
          2: 'Low',
        },
        applicationType: {
          1: 'Resource application',
          2: 'Resource termination',
        }
      }
    },
    created() {
      this.details = this.todoItem;
    },
    computed: {
      ...mapState(['todoItem']),
    },
    watch: {
      'todoItem': function (newDate) {
        this.details = newDate;
      }
    }
  }
</script>

<style lang="less" scoped>
  .base-station {
    /*padding: 20px 10px;*/
    margin-top: 15px;

    .base-station-info {
      background: rgba(249, 247, 251, 1);
      padding: 20px 40px;
      display: flex;

      .base-img {
        width: 100px;
        height: 90px;
        background: #ccc;
      }

      .base-station-info-left {
        div:nth-of-type(2) {
          text-align: center;
          margin-top: 15px;
        }
      }

      .base-station-info-right {
        margin-left: 20px;

        div {
          padding: 3px 0;
        }
      }

      .base-state {
        color: #999999;
        font-size: 13px;
      }

      .base-params {
        display: flex;
        margin-top: 25px;

        div {
          margin-right: 40px;
          text-align: center;

          p {
            font-size: 13px;
          }

          p:nth-of-type(1) {
            color: black;
          }

          p:nth-of-type(2) {
            color: #999999;
          }
        }
      }
    }

    .header-title {
      position: relative;
      height: 75px;
      .header-title-left {
        position: absolute;
        left: 0;
      }

      .header-title-right {
        position: absolute;
        right: 0;
        div {
          font-size: 14px;
          text-align: right;
          p {
            margin: 10px 0;
            color: #999999;
          }
        }

        .pending {
          display: inline-block;
          line-height: 32px;
          background: #f2f2f2;
          padding: 0 10px;
        }

        .approval {
          display: inline-block;
          line-height: 32px;
          background: #00cc00;
          color: #fff;
          padding: 0 10px;
        }

        .refuse {
          display: inline-block;
          line-height: 32px;
          background: red;
          color: #fff;
          padding: 0 10px;
        }
      }
    }
  }
</style>
